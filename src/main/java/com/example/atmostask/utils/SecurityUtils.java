package com.example.atmostask.utils;

import com.example.atmostask.entities.User;
import com.example.atmostask.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtils {
    public User getCurrentUser() {
        CustomUserDetails customUser = null;
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            customUser = (CustomUserDetails) authentication.getPrincipal();
            if (customUser.getUser() != null) {
                user = customUser.getUser();
            }
        }
        return user;
    }
}
