package com.example.secondarycache.domain;

import com.example.secondarycache.domain.post.Post;
import com.example.secondarycache.domain.post.PostRepository;
import com.example.secondarycache.domain.user.User;
import com.example.secondarycache.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CacheTest {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Order(1)
    @PostConstruct
    public void test() {
        User user = userRepository.save(User.builder()
                .name("김범진")
                .age(18)
                .build());

        Post post = postRepository.save(Post.builder()
                .title("제목1")
                .content("내용1")
                .user(user)
                .build());
    }

    @Order(2)
    @PostConstruct
    public void test2() {
        Post post = postRepository.findById(1L).get();

        System.out.println();
        System.out.println("1 : " + post);
        System.out.println();

        Post post2 = postRepository.findById(1L).get();

        System.out.println("2 : " + post2);

        Post post3 = postRepository.findById(1L).get();

        System.out.println("3 : " + post3);

        Post post4 = postRepository.findById(1L).get();

        System.out.println("4 : " + post4);
    }

}
