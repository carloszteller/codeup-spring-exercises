package com.codeup.springblogapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "This is my homepage!");
        return "home";
    }

    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @PostMapping("/")
    public String postSayHello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", "Hello, " + name + "!");
        return "home";
    }

    @GetMapping("/roll-dice")
    public String rollDice(Model model) {
        model.addAttribute("title", "Roll Dice");
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{dice}")
    public String getDiceRoll(@PathVariable int dice, Model model) {
        model.addAttribute("title", "Dice Roll Results");

        int random = (int) Math.floor((Math.random() * 6) + 1);

        if(dice == random) {
            model.addAttribute("result", "You chose: " + dice + ". The dice landed on: " + random + ". You got it right!");
        } else {
            model.addAttribute("result", "You chose: " + dice + ". The dice landed on: " + random + ". Sorry! Wrong guess...");
        }

        return "roll-dice";
    }
}
