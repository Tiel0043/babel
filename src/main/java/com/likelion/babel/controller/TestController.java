package com.likelion.babel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class TestController {

//    @Value("${openai.model}")
//    private String model;
//
//    @Value("${openai.api.url}")
//    private String apiURL;
//
//    private final RestTemplate restTemplate;

//    @GetMapping("/test1")
//    public String chat(){
//        ChatGPTRequest request = new ChatGPTRequest(model, "한글에 대해 50자 이내로 설명해줘");
//        ChatGPTResponse chatGPTResponse = restTemplate.postForObject(apiURL, request, ChatGPTResponse.class);
//        System.out.println(chatGPTResponse.getChoices().get(0).getMessage().getContent());
//        return "ok";
//    }

}
