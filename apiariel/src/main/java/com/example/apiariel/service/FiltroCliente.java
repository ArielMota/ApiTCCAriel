/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import static com.example.apiariel.config.Autenticacao.key;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author arielmota
 */
public class FiltroCliente extends org.springframework.web.filter.GenericFilterBean {
   

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Não foi possível validar o token!");
        }
        String token = header.substring(7);
        try{
           
            Jwts.parser()
                  
                    
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            
        }catch(JwtException e){
            throw new ServletException(e);
        }
         fc.doFilter(sr, sr1);
    }

}