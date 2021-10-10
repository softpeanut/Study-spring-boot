package com.example.mailsender.security.jwt;

import com.example.mailsender.exception.InvalidTokenException;
import com.example.mailsender.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private String SECRET_KEY;

    @Value("${jwt.header}")
    private String HEADER;

    @Value("${jwt.prefix}")
    private String PREFIX;

    @Value("${jwt.exp}")
    private Long TOKEN_EXPIRATION_TIME;

    private final CustomUserDetailsService customUserDetailsService;

    public void setSecretKey(@Value("${jwt.secret}") String secretKey) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String email) {
        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsername(token).getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER);

        if(StringUtils.hasText(token) && token.startsWith(PREFIX)) {
            return token.substring(7);
        }
        return null;
    }

    public Claims getUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public boolean validateToken(String token) {
        return !getUsername(token)
                .getExpiration()
                .before(new Date());
    }

}
