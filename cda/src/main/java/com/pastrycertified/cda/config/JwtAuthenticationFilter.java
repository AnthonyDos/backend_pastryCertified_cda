package com.pastrycertified.cda.config;

import com.pastrycertified.cda.repository.AdminRepository;
import com.pastrycertified.cda.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        String userEmail;
        String jwt;

        final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            userEmail = jwtUtils.extractUsername(jwt);


        //userEmail = jwtUtils.extractUsername(jwt);
        System.out.println(jwt);
       // System.out.println(userEmail);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (!userRepository.findByEmail(userEmail).isEmpty()) {
                UserDetails userDetails = userRepository.findByEmail(userEmail)
                        .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable lors de la validation JWT"));
                System.out.println(userDetails);
                if (jwtUtils.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    System.out.println(userDetails.getAuthorities() + " authoritie");
                    System.out.println(userDetails + " details");
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

            if (!adminRepository.findByEmail(userEmail).isEmpty()) {
                UserDetails userDetail = adminRepository.findByEmail(userEmail)
                        .orElseThrow(() -> new EntityNotFoundException("admin introuvable lors de la validation JWT"));

                if (jwtUtils.isTokenValid(jwt, userDetail)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                    System.out.println(userDetail.getAuthorities() + " authoritie admin");
                    System.out.println(userDetail + " detail admin");
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(" Token expired ");
        }



//        filterChain.doFilter(request, response);
    }


}
