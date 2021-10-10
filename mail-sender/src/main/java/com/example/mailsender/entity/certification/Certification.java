package com.example.mailsender.entity.certification;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@RedisHash
public class Certification implements Serializable {
    @Id
    private String email;

    @Indexed
    private String code;
    private boolean certified;

    @TimeToLive
    private Integer codeExp;
}
