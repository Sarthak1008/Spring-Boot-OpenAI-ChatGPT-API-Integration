package com.example.ChatGPT.with.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ChatGPT.with.SpringBoot.entities.ChatGPTRequest;
import com.example.ChatGPT.with.SpringBoot.entities.ChatGPTResponse;

@RestController
@RequestMapping("/bot")
public class CustomBotContoller {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGptResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
