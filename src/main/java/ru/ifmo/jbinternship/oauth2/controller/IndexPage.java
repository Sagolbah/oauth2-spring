package ru.ifmo.jbinternship.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexPage extends Page {

    @GetMapping({"", "/", "/home"})
    public String index() {
        return "IndexPage";
    }
}
