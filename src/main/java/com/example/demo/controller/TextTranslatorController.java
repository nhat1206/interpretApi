package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TextTranslatorService;

@RestController
@RequestMapping("/translator")
public class TextTranslatorController {
	@Autowired
    private TextTranslatorService textTranslatorService;

    @PostMapping("/translate")
    public String translateText(@RequestBody String text, @RequestParam String targetLanguage) {
        return textTranslatorService.translateText(text, targetLanguage);
    }
}
