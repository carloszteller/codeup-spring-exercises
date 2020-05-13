package com.codeup.springblogapp;

import com.codeup.springblogapp.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdController {
    // Dependency Injection
    private AdRepository adRepository;

    public AdController(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @GetMapping("/spring-ads")
    @ResponseBody
    public String getAds() {
        String ads = "<ul>";

        for(Ad ad : this.adRepository.findAll()) {
            ads += "<li>" + ad.getTitle() + "<br>" + ad.getDescription() + "</li>";
        }

        ads += "</ul>";

        return ads;
    }
}
