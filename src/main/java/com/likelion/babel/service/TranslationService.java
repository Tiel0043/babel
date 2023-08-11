package com.likelion.babel.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.babel.dto.papago.TranslationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class TranslationService {

    @Value("${papago.client.id}") // application.properties에서 설정한 값 사용
    private String clientId;

    @Value("${papago.client.secret}")
    private String clientSecret;

    private final String apiURL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation";

    public TranslationResponse translateText(String sourceText, String sourceLang, String targetLang) {
        try {
            String encodedText = URLEncoder.encode(sourceText, "UTF-8");
            String postParams = "source=" + sourceLang + "&target=" + targetLang + "&text=" + encodedText;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
            headers.set("X-NCP-APIGW-API-KEY", clientSecret);

            HttpEntity<String> requestEntity = new HttpEntity<>(postParams, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<TranslationResponse> response = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity, TranslationResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                // 에러 처리를 위해 예외를 던지거나, 에러 메시지를 담아서 반환할 수 있습니다.
                throw new RuntimeException("Error: " + response.getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            // 에러 처리를 위해 예외를 던지거나, 에러 메시지를 담아서 반환할 수 있습니다.
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
