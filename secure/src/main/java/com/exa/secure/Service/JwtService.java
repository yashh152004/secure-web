package com.exa.secure.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class JwtService {


 @SuppressWarnings("deprecation")
public String generateToken(String username) {
 Map<String, Object> claims = new HashMap<>(); // Claims can include custom data (e.g., roles, permissions)
 claims.put("username", username); // Adding custom claim

 return Jwts.builder()
 .setClaims(claims) // Add claims to the token
 .setSubject(username) // Set the subject (e.g., the username)
 .setIssuedAt(new Date(System.currentTimeMillis())) // Current time as issue time
 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3)) // Token expiration time (3 hours)
 .signWith(SignatureAlgorithm.HS256, getKey()) // Sign the token with the secret key
 .compact(); // Generate the token
 }

 @SuppressWarnings("FieldMayBeFinal")
 private String secretKey="your-256-bit-secret";
 public Key getKey()
 {
	byte[] getBytes =Decoders.BASE64.decode(secretKey);
	return Keys.hmacShaKeyFor(getBytes);
 }
}