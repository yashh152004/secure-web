package com.exa.secure.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

 public String generateSecretKey() {
 try {
 KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
 SecretKey secretKey = keyGen.generateKey();
 System.out.println("Secret Key : " + secretKey.toString());
 return Base64.getEncoder().encodeToString(secretKey.getEncoded());
 } catch (NoSuchAlgorithmException e) {
 throw new RuntimeException("Error generating secret key", e);
 }
}
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

 private java.security.Key getKey() {
	 String secret = "your-256-bit-secret"; // Use a strong secret in production
	 return io.jsonwebtoken.security.Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8));
 }
 public Key getKey()
 {
	byte[] getBytes =Decoders.BASE64.decode(secretKey);
	return Keys=hmacShaKeyFor(KeyBytes)
 }
}