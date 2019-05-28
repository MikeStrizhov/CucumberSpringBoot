package org.mgs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        logRequestDetails(request);
        return execution.execute(request, body);
    }
    private void logRequestDetails(HttpRequest request) {
        log.info("Headers: {}", request.getHeaders());
        log.info("Request Method: {}", request.getMethod());
        log.info("Request URI: {}", request.getURI());
    }
}
