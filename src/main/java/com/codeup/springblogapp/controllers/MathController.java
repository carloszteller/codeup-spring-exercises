package com.codeup.springblogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
//    @GetMapping("/add/{num1}/and/{num2}")
//    @ResponseBody
//    public String add(@PathVariable int num1, @PathVariable int num2) {
//        return num1 + " + " + num2 + " = " + (num1 + num2);
//    }
//
//    @GetMapping("/subtract/{num1}/from/{num2}")
//    @ResponseBody
//    public String subtract(@PathVariable int num1, @PathVariable int num2) {
//        return num2 + " - " + num1 + " = " + (num2 - num1);
//    }
//
//    @GetMapping("/multiply/{num1}/and/{num2}")
//    @ResponseBody
//    public String multiply(@PathVariable int num1, @PathVariable int num2) {
//        return num1 + " * " + num2 + " = " + (num1 * num2);
//    }
//
//    @GetMapping("/divide/{num1}/by/{num2}")
//    @ResponseBody
//    public String divide(@PathVariable int num1, @PathVariable int num2) {
//        return num1 + " / " + num2 + " = " + (num1 / num2);
//    }

    @GetMapping("/{operation}/{num1}/{keyword}/{num2}")
    @ResponseBody
    public String math(@PathVariable String operation, @PathVariable int num1, @PathVariable String keyword, @PathVariable int num2) {
        switch (operation) {
            case "add":
                return num1 + " + " + num2 + " = " + (num1 + num2);
            case "subtract":
                return num2 + " - " + num1 + " = " + (num2 - num1);
            case "multiply":
                return num1 + " * " + num2 + " = " + (num1 * num2);
            case "divide":
                return num1 + " / " + num2 + " = " + (num1 / num2);
            default:
                return "Math not good...";
        }
    }
}
