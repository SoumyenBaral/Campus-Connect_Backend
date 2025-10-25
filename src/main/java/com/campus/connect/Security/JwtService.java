package com.campus.connect.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service // Mark this as a Spring component
public class JwtService {

    // IMPORTANT: Make sure this property is set in your application.properties or application.yml
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration; // e.g., 86400000 milliseconds (24 hours)

    // ------------------- TOKEN GENERATION -------------------

    public String generateToken(UserDetails userDetails) {
        // Add the role as a custom claim for easy access
        Map<String, Object> claims = new HashMap<>();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        claims.put("role", role);
        
        return buildToken(claims, userDetails);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Use the configured expiration time
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)) 
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ------------------- TOKEN VALIDATION & EXTRACTION -------------------

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ------------------- PRIVATE UTILITY -------------------

    private Key getSigningKey() {
        // Decodes the base64-encoded secret key
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}