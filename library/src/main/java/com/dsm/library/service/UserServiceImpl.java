package com.dsm.library.service;

import com.dsm.library.controller.request.UserRequest;
import com.dsm.library.domain.user.User;
import com.dsm.library.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public String register(UserRequest request) {
        try {
            userRepository.save(
                    User.builder()
                            .id(request.getId())
                            .password(request.getPassword())
                            .build()
            );
            return "Success!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

}
