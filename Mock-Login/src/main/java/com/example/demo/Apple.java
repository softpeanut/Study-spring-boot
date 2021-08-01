package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Apple {
    private List<User> users = new ArrayList<>();

    @GetMapping("/user")
    public List<User> user() {
        return users;
    }

    @PostMapping("/user")
    public void register(@RequestBody User user) {
        users.add(user);
    }

    @PostMapping("/auth")
    public String auth(@RequestBody User auth) {
        for (User user : users) {
            if(user.getUserId().equals(auth.getUserId())) {
                if(user.getUserPassword().equals(auth.getUserPassword())) {
                    return "로그인 성공";
                }
            }
        }
        return "로그인 실패";
    }
}
