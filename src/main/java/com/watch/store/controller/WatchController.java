package com.watch.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class WatchController {

    @GetMapping("/")
    public String viewTemplate() {

        return "login";
    }
}
