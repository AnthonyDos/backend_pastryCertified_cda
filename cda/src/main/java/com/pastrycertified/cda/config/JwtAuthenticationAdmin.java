//package com.pastrycertified.cda.config;
//
//import com.pastrycertified.cda.repository.AdminRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.persistence.EntityNotFoundException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationAdmin extends OncePerRequestFilter {
//
//    private final JwtUtils jwtUtils;
//    private final AdminRepository adminRepository;
//
//    private static final String AUTHORIZATION = "Authorization";
//    private static final String BEARER = "Bearer ";
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        String authHeader = request.getHeader(AUTHORIZATION);
//        String userEmail;
//        String jwt;
//
//        if (authHeader == null || !authHeader.startsWith(BEARER)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        jwt = authHeader.substring(7);
//        userEmail = jwtUtils.extractUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = adminRepository.findByEmail(userEmail)
//                    .orElseThrow(() -> new EntityNotFoundException("Admin introuvable lors de la validation JWT"));
//            if (jwtUtils.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
