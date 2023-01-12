package com.pastrycertified.cda.config;

import com.pastrycertified.cda.repository.AdminRepository;
import com.pastrycertified.cda.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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
            final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
        try {
            String authHeader = request.getHeader(AUTHORIZATION);
            String userEmail;
            String jwt;

            if (authHeader == null || !authHeader.startsWith(BEARER)) {
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            userEmail = jwtUtils.extractUsername(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                if (!userRepository.findByEmail(userEmail).isEmpty()) {
                    UserDetails userDetails = userRepository.findByEmail(userEmail)
                            .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable lors de la validation JWT"));
                    if (jwtUtils.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        System.out.println(userDetails.getAuthorities() + " authoritie");
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }

                if (!adminRepository.findByEmail(userEmail).isEmpty()) {

                    UserDetails userDetail = adminRepository.findByEmail(userEmail)
                            .orElseThrow(() -> new EntityNotFoundException("admin token corrompu"));

                    if (jwtUtils.isTokenValid(jwt, userDetail)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetail, "le token est expiré", userDetail.getAuthorities());
                        System.out.println(userDetail.getAuthorities() + " authoritie admin");
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            response.getWriter().write("token expired pour l'utilisateur ");
            response.getWriter().write(
                    e.getClaims().getSubject());
            response.setStatus(401);
            log.error(e.getMessage());
        }




        //filterChain.doFilter(request, response);
    }


}
