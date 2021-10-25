package com.example.atmostask.controllers;

import com.example.atmostask.models.res.AdminNewsBoardResponse;
import com.example.atmostask.services.news.NewsService;
import com.example.atmostask.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    private final NewsService newsService;
    private final SecurityUtils securityUtils;

    @Autowired
    public AdminPageController(NewsService newsService, SecurityUtils securityUtils) {
        this.newsService = newsService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/all/news")
    public String getUserList(Model model) {
        model.addAttribute("user", securityUtils.getCurrentUser());
        return "cabinet/adminpage";
    }

    @GetMapping("/all/news/list")
    @ResponseBody
    public List<AdminNewsBoardResponse> getBoardList() {
        return newsService.getAllNewsForAdmin();
    }

    @PostMapping(value = "/board/cancel/{id}")
    public String cancelNewsBoard(Model model, @PathVariable(value = "id") Long id) {
        newsService.cancelNewsBoard(id);
        return "redirect:/api/board";
    }

    @PostMapping(value = "/board/approve/{id}")
    public String approveNewsBoard(Model model, @PathVariable(value = "id") Long id) {
        newsService.approveNewsBoard(id);
        return "redirect:/api/board";
    }
}
