package org.mgs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class RestController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<String> responce;
    private RestTemplate restTemplate;
    //private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public RestController(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public void executeGetByUrl(String url) {
        responce = restTemplate.getForEntity(url, String.class);
    }

    public boolean isResponceCode200Ok() {
        if (responce == null){
            return false;
        }
        return  responce.getStatusCode() ==  HttpStatus.OK;
    }

    public void executePopstXMLByUrl(String url, String requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
        responce = restTemplate.postForEntity(url, entity , String.class);
    }
}