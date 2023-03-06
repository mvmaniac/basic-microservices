# Basic Microservices

### 1. 책, 강의 실습 예제 기반

- [Spring Cloud로 개발하는 마이크로서비스 애플리케이션](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%81%B4%EB%9D%BC%EC%9A%B0%EB%93%9C-%EB%A7%88%EC%9D%B4%ED%81%AC%EB%A1%9C%EC%84%9C%EB%B9%84%EC%8A%A4 "Spring Cloud로 개발하는 마이크로서비스 애플리케이션")
  참고
- [스프링 마이크로서비스 코딩 공작소](https://kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9791160506815&orderClick=JAj "스프링 마이크로서비스 코딩 공작소")
  참고

### 2. 차이점

- Spring Boot 3, Gradle 8 기반

### 3. TODO

- Spring Boot 3 버전에서 테스트 필요
- JwtTokenProperties 설정 파일을 Environment로 변경 (Config 변경 시 적용이 되기 위해...)

### 4. 참고

- gradle bootRun 실행 방법 (Windows 기준)
  - [spring-boot-command-line-arguments](https://www.baeldung.com/spring-boot-command-line-arguments "spring-boot-command-line-arguments")
    참고
  - 인자 전달 방식 3가지
    - VM options (Environment로 읽을 수 있음)  
      `-Dspring.profiles.active=dev`
    - Program arguments (우선순위가 제일 높음, CommandLineRunner or ApplicationRunner로 읽을 수 있음)  
      `--spring.profiles.active=dev`
    - Environment variables  
      `spring.profiles.active=dev`
  - build.gradle 수정 필요
    - VM options를 좀 더 쉽게 사용하고 싶은 경우
      ```groovy
      bootRun {
        systemProperties = System.properties as Map<String, ?>
      }
      ```
    - Program arguments를 좀 더 쉽게 사용하고 싶은 경우
      ```groovy
      if (project.hasProperty('args')) {
        args project.args.split(',') as String[]
      }
      ```
  - gradlew 파일 위치로 이동, 아래 명령어 실행 (Windows 기준)
    - 기본
      ```bash
      gradlew.bat bootRun
    - 멀티 모듈인 경우
      ```bash
      gradlew.bat :<모듈명>:bootRun
    - 인자 사용, VM options
      ```bash
      gradlew.bat bootRun -Dserver.port=9003 -Dspring.profiles.active=dev -Dvm.custom=vmCustom
    - 인자 사용, Program arguments
      ```bash
      gradlew.bat bootRun -Pargs=--server.port=9003,--spring.profiles.active=dev,--prog.custom=progCust
- java -jar 실행 방법 (Windows 기준)
  - 인자 사용, VM options
    ```bash
    java -Dserver.port=9003 -Dspring.profiles.active=dev -Dvm.custom=vmCustom -jar <jar 파일명>
  - 인자 사용, Program arguments
    ```bash
    java -jar <jar 파일명> --server.port=9003 --spring.profiles.active=dev --prog.custom=progCustom
- 배포 해보기 (미완료)
  - 소스는 git pull or 직접 업로드
  - docker network 생성 및 확인
    ```bash
    docker network create --gateway 172.18.0.1 --subnet 172.18.0.0/16 basic-microservices  
    docker network ls
    docker network inspect basic-microservices
  - Dockerfile 사용 (2.3.x 이상)
  - bootBuildImage 사용 (2.4.x 이상)
