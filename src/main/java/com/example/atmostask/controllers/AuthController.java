package com.example.atmostask.controllers;

import com.example.atmostask.models.custom.ResponseMessages;
import com.example.atmostask.models.req.SignUpRequest;
import com.example.atmostask.services.custom.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign/in")
    public String getHomePage() {
        return "login";
    }


    @GetMapping("/sign/up")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerReq", new SignUpRequest());
        return "register";
    }

    @PostMapping("/sign/up")
    public String registerUser(@Valid @ModelAttribute("registerReq") SignUpRequest request, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.saveUser(request);
            return "login";
        }
        return "register";
    }

    @GetMapping("/sign/in/error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            BadCredentialsException ex = (BadCredentialsException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null)
                errorMessage = ResponseMessages.INVALID_CREDENTIALS;
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}
