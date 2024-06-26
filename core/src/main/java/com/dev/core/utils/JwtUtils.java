package com.dev.core.utils;

import java.security.Key;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private static Logger logger = LogManager.getLogger(JwtUtils.class);

	private String jwtSecret = "=======================Spring=Security==========================";

	private int jwtExpirationSecond = 300;// 5 minute
	private int jwtExpirationMs = jwtExpirationSecond * 1000;

	public String generateJwtToken(Authentication authentication) {
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Date expirationTime = new Date((new Date()).getTime() + jwtExpirationMs);
		JwtBuilder builder = Jwts.builder().claim("role", authentication.getAuthorities())
				.subject((userPrincipal.getUsername()))
				.issuedAt(new Date())
				.expiration(expirationTime)
				.signWith(key());
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("token", builder.compact());
		jsonObject.addProperty("token-expiry", expirationTime.toString());
		jsonObject.addProperty("token-type", "Bearer ");
		return jsonObject.toString();
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(key()).build().parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
