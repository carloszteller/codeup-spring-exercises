package com.codeup.springblogapp;

import com.codeup.springblogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    // Dependency Injection
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("title", "View All Posts");
        model.addAttribute("posts", postRepository.findAll());

        return "/posts/index";
    }

    @GetMapping("/post/show/{id}")
    public String postById(@PathVariable long id, Model model) {
//        Post post = new Post(id, "My Post Title", "Hello! Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        model.addAttribute("title", "View Single Post");
        model.addAttribute("post", postRepository.getOne(id));
        model.addAttribute("user", userRepository.getOne((long) 1));

        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreate(Model model) {
        model.addAttribute("title", "Create New Post");

        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(@RequestParam("title") String title, @RequestParam("body") String body) {
        Post post = new Post(title, body);
        User user = userRepository.getOne((long) 1);

        post.setUser(user);

        postRepository.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/post/update/{id}")
    public String getUpdate(@PathVariable long id, Model model) {
        model.addAttribute("title", "Update Post");
        model.addAttribute("post", postRepository.getOne(id));

        return "/posts/update";
    }

    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable long id, @RequestParam("title") String title, @RequestParam("body") String body) {
        Post post = postRepository.getOne(id);
        User user = userRepository.getOne((long) 1);

        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);
        postRepository.save(post);

        return "redirect:/post/show/" + id;
    }

    @GetMapping("/post/delete/{id}")
    public String getUpdate(@PathVariable long id) {
        postRepository.deleteById(id);

        return "redirect:/posts";
    }
}
