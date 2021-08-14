package com.example.jwtpractice2.jwt;

import com.example.jwtpractice2.dto.TokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    private static final Long tokenExpiration = 1000 * 60 * 60 * 2L;

/*    @Value("${jwt.secret}")
    private String secretkey;*/

    private final Key secretkey;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretkey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        this.secretkey = Keys.hmacShaKeyFor(keyBytes);
    }

/*    protected String init() {
        return Base64.getEncoder().encodeToString(secretkey.getEncoded());
    }*/

    public TokenResponse createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpiration))
                .signWith(secretkey, SignatureAlgorithm.HS256)
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
        return Jwts.parserBuilder().setSigningKey(secretkey).build().parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
