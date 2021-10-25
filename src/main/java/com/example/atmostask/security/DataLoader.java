package com.example.atmostask.security;

import com.example.atmostask.entities.Role;
import com.example.atmostask.entities.User;
import com.example.atmostask.repositories.RoleRepository;
import com.example.atmostask.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.sql.init.mode}")
    private String initialMode;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

//        Saving admin when initialization mode is always
        if (initialMode.equals("always")) {
            preloadRoles();
            initializeAdmin();
        }
    }


    private void preloadRoles() {
        if (roleRepository.findAll().size() == 0) {
            Role adminRole = new Role();
            adminRole.setActive(true);
            adminRole.setName(AuthoritiesConstants.ADMIN);
            roleRepository.save(adminRole);
            Role userRole = new Role();
            userRole.setActive(true);
            userRole.setName(AuthoritiesConstants.USER);
            roleRepository.save(userRole);
            LOG.info("Roles saved");
        }

    }

    private void initializeAdmin() {
        Role role = roleRepository.findByName(AuthoritiesConstants.ADMIN);
        if (role == null) {
            LOG.info("Roles not found");
            return;
        }
        User user = userRepository.findByUserName("admin");
        if (user == null) {
            User admin = new User("admin", "example@gmail.com", passwordEncoder.encode("12345"), 18,
                    Collections.singleton(role));
            admin.setActive(true);
            userRepository.save(admin);
        }
    }
}
