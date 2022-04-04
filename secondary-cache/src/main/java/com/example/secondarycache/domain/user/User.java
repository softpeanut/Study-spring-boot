package com.example.secondarycache.domain.user;

import com.example.secondarycache.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private Long id;

    private String name;

    private Integer age;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

}
