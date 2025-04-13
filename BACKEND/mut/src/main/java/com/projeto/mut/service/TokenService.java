package com.projeto.mut.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.mut.Entity.Empresa;

@Service
public class TokenService {
    
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Empresa empresa) {
		
		try {
	       Algorithm algorithm = Algorithm.HMAC256(secret);
	       String token = JWT.create()
	    		          .withIssuer("auth-api")
	    		          .withSubject(empresa.getLogin())
	    		          .withExpiresAt(genExpiracaoData())
	    		          .sign(algorithm);
	       return token;
	    		          
		} catch(JWTCreationException excecao) {
			throw new RuntimeException("Erro ", excecao);
		}
		
	}
	
	public String validarToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
			        .getSubject();
		} catch(JWTVerificationException excecao) {
			return "";
		}
	}
	
	private Instant genExpiracaoData() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
