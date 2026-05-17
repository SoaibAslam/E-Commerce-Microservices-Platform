package com.ekartgateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

	private final String SECRET = "mysecretkeymysecretkeymysecretkey"; // must match AuthMS

	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}