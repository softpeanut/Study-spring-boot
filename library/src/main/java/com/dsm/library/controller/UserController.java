package com.dsm.library.controller;

import com.dsm.library.controller.request.UserRequest;
import com.dsm.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserRequest request) {
        return userService.register(request);
    }

}
