package com.pw.sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String userList() {
        return "redirect:/fast/index";
    }

    @GetMapping("/t")
    public String t() {
        return "temp";
    }

}
