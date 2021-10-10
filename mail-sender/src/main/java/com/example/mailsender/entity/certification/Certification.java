package com.example.mailsender.entity.certification;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RedisHash
public class Certification implements Serializable {
    @Id
    private String email;

    @Indexed
    private String code;
    private Certified certified;

    @TimeToLive
    private Integer codeExp;

    public Certification updateCertified(Certified certified) {
        this.certified = certified;
        return this;
    }

}
