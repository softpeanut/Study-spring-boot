package com.dsm.library.controller;

import com.dsm.library.controller.request.UserRequest;
import com.dsm.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String SignUp(@RequestBody UserRequest request) {
        return userService.register(request);

    }

}
