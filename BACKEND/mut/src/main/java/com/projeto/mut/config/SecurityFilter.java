package com.projeto.mut.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projeto.mut.repository.EmpresaRepository;
import com.projeto.mut.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	TokenService tokenService;
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    String uri = request.getRequestURI();
	    
	    var token = this.recoverToken(request);
	    
	    if (token != null) {
	        var login = tokenService.validarToken(token);
	  
	        if (login != null) {
	            UserDetails user = empresaRepository.findByLogin(login);
	            if (user != null) {
	                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	        }
	    } else {
	        System.out.println("Nenhum token informado.");
	    }
	    filterChain.doFilter(request, response);
	}



	private String recoverToken(HttpServletRequest request) {
	    var authHeader = request.getHeader("Authorization");
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        return null;
	    }
	    return authHeader.replace("Bearer ", "").trim();
	}

}
