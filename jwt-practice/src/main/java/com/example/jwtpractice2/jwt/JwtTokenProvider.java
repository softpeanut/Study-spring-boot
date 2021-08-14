package com.example.jwtpractice2.jwt;

import com.example.jwtpractice2.dto.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;
    private Long tokenExpiration = 1000 * 60 * 60 * 2L;

    @Value("${jwt.secret}")
    private String secretkey;

    protected String init() {
        return Base64.getEncoder().encodeToString(secretkey.getBytes());
    }

    public TokenResponse createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, init())
                .compact();

        return new TokenResponse(token);
    }

    public boolean validateToken(String jwtToken) {
        try {
            return !getUserPk(jwtToken)
                    .getExpiration()
                    .before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token).getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
