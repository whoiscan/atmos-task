package com.example.atmostask.services.custom;

import com.example.atmostask.models.req.SignUpRequest;

public interface UserService {
    void saveUser(SignUpRequest signUpRequest);
}
