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
    private EmailService emailService;

    public PostController(PostRepository postRepository, UserRepository userRepository, EmailService emailService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
       model.addAttribute("posts", postRepository.findAll());

        return "/posts/index";
    }

    @GetMapping("/post/show/{id}")
    public String postById(@PathVariable long id, Model model) {
//        Post post = new Post(id, "My Post Title", "Hello! Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        model.addAttribute("post", postRepository.getOne(id));

        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String getCreate(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(@ModelAttribute Post post) {
        User user = userRepository.getOne(1L);
        post.setUser(user);

        postRepository.save(post);

        emailService.prepareAndSend(post, "New ad created!", "Title: " + post.getTitle() + "\n" + "Body: " + post.getBody());

        return "redirect:/posts";
    }

    @GetMapping("/post/update/{id}")
    public String getUpdate(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepository.getOne(id));

        return "/posts/update";
    }

    @PostMapping("/post/update/{id}")
    public String postUpdate(@ModelAttribute Post post) {
        User user = userRepository.getOne(1L);
        post.setUser(user);

        postRepository.save(post);

        return "redirect:/post/show/" + post.getId();
    }

    @GetMapping("/post/delete/{id}")
    public String getUpdate(@PathVariable long id) {
        postRepository.deleteById(id);

        return "redirect:/posts";
    }
}
