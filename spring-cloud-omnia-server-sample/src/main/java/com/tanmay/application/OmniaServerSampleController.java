package com.tanmay.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("OmniaServer")
public class OmniaServerSampleController {

    @GetMapping("/")
    public String ping(){
        return "OK";
    }
}
