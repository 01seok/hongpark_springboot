package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(@RequestParam(name = "name", required = false, defaultValue = "guest") String name, Model model) {
        model.addAttribute("username", name);
        return "greetings"; // templates/greetings.mustache -> 브라우저 전송 !
    }

    @GetMapping("/bye")
    public String seeYouNext(@RequestParam(name = "name", required = false, defaultValue = "guest")String name, Model model) {
        model.addAttribute("nickname", name);
        return "goodbye";
    }
}
