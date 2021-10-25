package com.example.atmostask.controllers;

import com.example.atmostask.models.req.NewBoardRequest;
import com.example.atmostask.models.res.NewsBoardResponse;
import com.example.atmostask.services.news.NewsService;
import com.example.atmostask.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPageController {
    private final NewsService newsService;
    private final SecurityUtils securityUtils;

    @Autowired
    public UserPageController(NewsService newsService, SecurityUtils securityUtils) {
        this.newsService = newsService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/all/news")
    public String getUserList(Model model) {
        List<NewsBoardResponse> responseList = newsService.getAllNewsForUser(securityUtils.getCurrentUser().getId());
        model.addAttribute("userBoard", responseList);
        model.addAttribute("newBoard", new NewBoardRequest());
        return "cabinet/userpage";
    }
}
