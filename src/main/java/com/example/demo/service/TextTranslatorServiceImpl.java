package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TextTranslatorServiceImpl implements TextTranslatorService{

	private static final Map<String, String> TRANSLATION_MAP = new HashMap<>();
    static {
        TRANSLATION_MAP.put("hello", "hallo");
        TRANSLATION_MAP.put("good morning", "Guten Morgen");
        TRANSLATION_MAP.put("good bye", "Verabschiedung");
        TRANSLATION_MAP.put("now", "Jetzt");
        TRANSLATION_MAP.put("Jetzt", "now");
        TRANSLATION_MAP.put("hallo", "hello");
        TRANSLATION_MAP.put("Guten Morgen", "good morning");
        TRANSLATION_MAP.put("Verabschiedung", "good bye");
        // Add more mappings if needed
    }

    @Override
    public String translateText(String text, String targetLanguage) {
        // Process input, remove leading/trailing whitespaces
        String input = text.trim().replaceAll("\"", "");;
        String translatedText = "";
        // Lookup translation in the map
        for (Map.Entry<String, String> entry : TRANSLATION_MAP.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(input)) {
            	translatedText = entry.getValue();
            }
        }
        return translatedText;
    }

}
