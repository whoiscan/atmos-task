package com.example.atmostask.services.custom;

import com.example.atmostask.entities.User;
import com.example.atmostask.models.req.SignUpRequest;
import com.example.atmostask.repositories.RoleRepository;
import com.example.atmostask.repositories.UserRepository;
import com.example.atmostask.security.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(SignUpRequest request) {
        User user = userRepository.findByUserName(request.getUsername());
        if (user == null) {
            User registerUser = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
                    Integer.parseInt(request.getAge()), Collections.singleton(roleRepository.findByName(AuthoritiesConstants.USER)));
            registerUser.setActive(true);
            userRepository.save(registerUser);
        }
    }
}
