package com.exa.secure;

import org.hibernate.hql.internal.ast.util.ASTUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                @SuppressWarnings("unused")
                String authHeader = request.getHeader("Authorization");
                String token=null;
                String userName=null;
                if(authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                    try {
                        userName = ASTUtil.extractUsername(token);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }