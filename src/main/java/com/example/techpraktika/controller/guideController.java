package com.example.techpraktika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class guideController {

    @GetMapping("/guide")
    public String showGuidePage(){
        return "guide";
    }
}
