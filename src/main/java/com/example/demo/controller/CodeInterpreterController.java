package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CodeInterpreterService;

@RestController
@RequestMapping("/interpreter")
public class CodeInterpreterController {
	@Autowired
    private CodeInterpreterService codeInterpreterService;

    @PostMapping("/interpret")
    public String interpretCode(@RequestBody String code) {
        return codeInterpreterService.interpretCode(code);
    }
}
