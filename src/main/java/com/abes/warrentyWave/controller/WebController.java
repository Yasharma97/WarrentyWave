package com.abes.warrentyWave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({"/", "/dashboard", "/claims", "/vehicles", "/contracts", "/customers"})
    public String index(Model model) {
        model.addAttribute("appName", "WarrantyWave");
        model.addAttribute("appTagline", "Automotive Warranty & Finance Management Suite");
        return "index";
    }
}
