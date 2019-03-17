package com.gdl.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/member-list.html")
    public String member_list() { return "member-list"; }
    @RequestMapping("/member-add.html")
    public String member_add() { return "member-add"; }
}
