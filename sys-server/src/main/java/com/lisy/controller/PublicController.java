package com.lisy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lisy
 * @Date: 2021/4/7
 * @version: 1.0
 * @Description: ""
 */
@RestController
@RequestMapping(value = "/public")
public class PublicController {
    @GetMapping("/test")
    public String index(){
        return "sagww2";
    }
}
