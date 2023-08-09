//package com.likelion.babel.dto;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//public class GptController {
//
//    @Value("${openai.model}")
//    private String model;
//
//    @Value(("${openai.api.url}"))
//    private String apiURL;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @GetMapping("/chat")
//    public ChatGPTResponse chat(@RequestParam("prompt") String prompt){
//        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
//        ChatGPTResponse chatGPTResponse = restTemplate.postForObject(apiURL, request, ChatGPTResponse.class);
//        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
//    }
//}
