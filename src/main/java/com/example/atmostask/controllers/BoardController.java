package com.example.atmostask.controllers;

import com.example.atmostask.models.req.NewBoardRequest;
import com.example.atmostask.services.news.NewsService;
import com.example.atmostask.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Controller
@RequestMapping("/api")
public class BoardController {
    private final NewsService newsService;
    private final SecurityUtils securityUtils;

    @Autowired
    public BoardController(NewsService newsService, SecurityUtils securityUtils) {
        this.newsService = newsService;
        this.securityUtils = securityUtils;
    }

    @GetMapping({"/", "/board"})
    public String getBoards(Model model) {
        model.addAttribute("newBoard", new NewBoardRequest());
        return "board";
    }

    @PostMapping("/board")
    public String createNewBoard(@Valid @ModelAttribute("newBoard") NewBoardRequest newBoardRequest, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            newsService.saveNewsBoard(securityUtils.getCurrentUser(), newBoardRequest);
            return "redirect:/api/board";
        }
        return "board";
    }

    @PostMapping(value = "/board/edit/{id}")
    public String editNewsBoard(@Valid @RequestBody @Size(max = 500, message = "Oграничение в 500 символов") String description,
                                @PathVariable(value = "id") Long id) {
        newsService.editNewsBoard(securityUtils.getCurrentUser(), description, id);
        return "redirect:/api/board";
    }


}
