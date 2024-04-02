package com.cross.user.controller;

import com.cross.user.entity.User;
import com.cross.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/seed")
    public void addSeedData() {

        User user = new User();
        user.setId(1);
        List<Long> createdRecipeList = new ArrayList<>();
        createdRecipeList.add((long)1);
        user.setCreatedRecipeList(createdRecipeList);
        userRepository.save(user);


    }
    @GetMapping("/get")
    private Iterable<User> get() {
        return userRepository.findAll();
    }

}
