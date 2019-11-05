package io.devfactory.license.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.devfactory.license.clients.OrganizationDiscoveryClient;
import io.devfactory.license.clients.OrganizationOAuth2RestTemplateClient;
import io.devfactory.license.clients.OrganizationRestTemplateClient;
import io.devfactory.license.clients.OrganizationFeignClient;
import io.devfactory.license.config.ServiceConfig;
import io.devfactory.license.model.License;
import io.devfactory.license.model.Organization;
import io.devfactory.license.repository.LicenseRepository;
import io.devfactory.license.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    private final OrganizationDiscoveryClient discoveryClient;
    private final OrganizationFeignClient feignClient;
    private final OrganizationOAuth2RestTemplateClient oauth2RestTemplateClient;
    private final OrganizationRestTemplateClient restTemplateClient;

    private final ServiceConfig config;

    // TODO: 설정 값은 config 서버로 설정
    @HystrixCommand(
        fallbackMethod = "buildFallbackLicenseList",
        threadPoolKey = "licenseByOrganizationThreadPool",
        threadPoolProperties = {
                @HystrixProperty(name="coreSize",value="30"), // 스레드 풀의 스레드 개수를 정의
                @HystrixProperty(name="maxQueueSize", value="10") // 스레드 풀 앞에 배치할 큐와 큐에 넣을 요청 수를 정의
        },
        commandProperties={
                @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"), // 호출 차단을 고려하는데 필요한 10초 동안 연속 호출 횟수 제어 (10번)
                @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"), // 회로 차단기를 차단하고 나서 해당 값만큼 호출한 후 실패해야하는 호출 비율 (75%)
                @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"), // 차단되고 나서 서비스의 회복상태를 확인할 때 까지 대기할 시간 (7초)
                @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"), // 서비스 호출 문제를 모니터할 시간 간격을 설정 (15초)
                @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5"), // 위에서 설정한 시간 간격 동안 통계를 수집할 횟수 (5개 버킷)

        }
    )
    public List<License> getLicensesByOrganizationId(String organizationId) {
        log.debug("LicenseService.getLicensesByOrganizationId Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        // 시간 지연 테스트 코드
        randomlyRunLong();

        return licenseRepository.findByOrganizationId(organizationId);
    }

    // 기본은 호출이 1000ms (1초) 보다 오래 걸릴 때 마다 호출을 중단
    @HystrixCommand(
//            commandProperties = {
//                @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="12000") // 타임아웃 12초
//            }
    )
    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        license.withComment(config.getExampleProperty());

        // 시간 지연 테스트 코드
        //randomlyRunLong();

        return retrieveOrganizationInfo(organizationId, clientType)
                .map(license::withOrganization).orElse(license);
    }

    public void saveLicense(License license) {
        license.withId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(String licenseId) {
        licenseRepository.deleteById(licenseId);
    }

    // TODO: 에러 처리
    // TODO: clientType enum 으로...
    private Optional<Organization> retrieveOrganizationInfo(String organizationId, String clientType) {

        Organization organization;

        switch (clientType) {
            case "feign":
                log.debug("I am using the feign client");
                String authToken = UserContextHolder.getContext().getAuthToken();
                organization = feignClient.getOrganization(authToken, organizationId);
                break;

            case "rest":
                log.debug("I am using the rest client");
                organization = restTemplateClient.getOrganization(organizationId);
                break;

            case "discovery":
                log.debug("I am using the discovery client");
                organization = discoveryClient.getOrganization(organizationId);
                break;

            case "auth2":
                log.debug("I am using the oauth2 rest client");
                organization = oauth2RestTemplateClient.getOrganization(organizationId);
                break;

            default:
                organization = restTemplateClient.getOrganization(organizationId);

        }

        return Optional.ofNullable(organization);
    }

    private List<License> buildFallbackLicenseList(String organizationId){
        return List.of(
            License.builder().organizationId(organizationId).productName("Sorry no licensing information currently available").build().withId("0000000-00-00000")
        );
    }

    private void randomlyRunLong(){
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1; // 3회 중 1회 지연
        if (randomNum == 3) sleep();
    }

    private void sleep(){
        try {
            log.debug("randomlyRunLong start sleep....");
            Thread.sleep(11000); // 11초 동안 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
