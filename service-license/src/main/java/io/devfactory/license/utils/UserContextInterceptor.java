package io.devfactory.license.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders httpHeaders = request.getHeaders();

        String correlationId = UserContextHolder.getContext().getCorrelationId();
        String authToken = UserContextHolder.getContext().getAuthToken();

        httpHeaders.add(UserContext.CORRELATION_ID, correlationId);
        httpHeaders.add(UserContext.AUTH_TOKEN, authToken);

        log.debug("UserContextInterceptor Correlation id: {}, AuthToken: {}", correlationId, authToken);

        return execution.execute(request, body);
    }

}
