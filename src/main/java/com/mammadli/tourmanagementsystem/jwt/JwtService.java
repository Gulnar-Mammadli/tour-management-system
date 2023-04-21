package com.mammadli.tourmanagementsystem.jwt;

import com.mammadli.tourmanagementsystem.config.MyUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtService {

    public static final String SECRET = "4D6351665468576D5A7134743777217A25432A462D4A614E645267556B586E32";

    @Autowired
    private MyUserDetailsService userDetailsService;

    public String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }


    private String createToken(Map<String, Object> claims, String userName) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        String rolesClaim = roles.toString();
        log.info("rolesClaim  {} ", rolesClaim);
        int start = rolesClaim.indexOf("[");
        int end = rolesClaim.indexOf("]");
        rolesClaim = rolesClaim.substring(start + 1, end);
        log.info("claims  {} ", rolesClaim);

        claims.put("roles", rolesClaim);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                //.addClaims(roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60)) // when setting an expiration "Date", we use milliseconds, the easiet way to set it is using the following format 1000(1000 milliseconds  = second)*60 (1 second * 60 =  a minutes) *60 (1 minute * 60 =  1 hour) -> 1000*60*60 = 1 hour
                // .signWith(Key key, SignatureAlgorithm alg) signs the constructed JWT with the specified key using the specified algorithm, producing a JWS.
                // For the key, we are using a function to created, and for the SignatureAlgorithm we are using the "HS256" hashing algorithm.
                // .compact() is the final step, and it builds the JWT and serializes it to a compact, URL-safe string according to the JWT Compact Serialization rules.
                .signWith(signingKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key signingKey() {
        byte[] keyDecoder = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyDecoder);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRoles(String token) {
        String claimRoles = extractAllClaims(token).get("roles", String.class);
        return  claimRoles;
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        log.info("extractRoles  {} ", extractRoles(token));
        return (username.equals(userDetails.getUsername())  && !isTokenExpired(token));
    }
}
