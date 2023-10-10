package com.dxc.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                  final FilterChain chain) throws ServletException, IOException {
    // look for Bearer auth header
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith("Bearer ")) {
      System.out.println("No token found in header");
      chain.doFilter(request, response);
      return;
    }

    final String token = header.substring(7);
    System.out.println("Got token: " + token);
    final String username = jwtUtils.validateTokenAndGetUsername(token);
    System.out.println("Got username: " + username);
    if (username == null) {
      // validation failed or token expired
      chain.doFilter(request, response);
      return;
    }

    // set user details on spring security context
    final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
    System.out.println(userDetails.getAuthorities());
    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    System.out.println(SecurityContextHolder.getContext().toString());
    // continue with authenticated user
    chain.doFilter(request, response);
  }
}