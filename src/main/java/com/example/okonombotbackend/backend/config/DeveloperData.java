package com.example.okonombotbackend.backend.config;

import com.example.okonombotbackend.security.entity.User;
import com.example.okonombotbackend.backend.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {

    //Repositories
    UserRepository userRepository;

    public DeveloperData(UserRepository userRepository) {
        this.userRepository = userRepository;
        //Initialize all repos
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Methods to run.

        User user = new User();
        user.setUsername("Adolf");
        user.setPassword("yippy");
        user.setEmail("takemeback@mail.com");

        userRepository.save(user);

    }

}

