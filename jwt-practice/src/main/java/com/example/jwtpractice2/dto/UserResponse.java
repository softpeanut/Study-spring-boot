package com.example.jwtpractice2.dto;

import com.example.jwtpractice2.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String email;

    public static UserResponse of(Member member) {
        return new UserResponse(member.getUsername());
    }
}
