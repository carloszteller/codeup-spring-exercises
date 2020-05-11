package com.codeup.springblogapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("title", "View All Posts");
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postById(@PathVariable long id, Model model) {
        Post post = new Post(id, "My Post Title", "Hello! Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");

        model.addAttribute("title", "View Single Post");
        model.addAttribute("post", post);

        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreate() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate() {
        return "create a new post";
    }
}
