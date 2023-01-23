package com.pastrycertified.cda.config;

import com.pastrycertified.cda.handlers.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@EnableWebSecurity // active la sécurité
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests(
                        (request) ->
                        {
                            try {
                                //toute url qui contient auth et toutes celles qui contiennet enregistrement
                                request.antMatchers(
                                                "/**/authenticate",
                                                "/**/register","**/addresses",
                                                "/**/register-admin",
                                                "/**/authenticate-admin",
                                                "/**/categories/all-categories"
                                )
                                        .permitAll()
                                        .antMatchers(
                                                "/**/users/",
                                                "/**/users/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                                        .antMatchers(
                                                "/**/register-pastrychef",
                                                "/**/shop/**",
                                                "/**/categories/**",
                                                "/**/options/**"
                                                ).access("hasRole('ROLE_ADMIN')")//authorisé ces url
                                        .antMatchers("/**/salary/**").access("hasRole('PASTRY_CHEF') or hasRole('ROLE_ADMIN')")
                                        .anyRequest()//toutes les autres requêtes doivent être authentifiés
                                        .authenticated()
                                        .and()
                                        .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .exceptionHandling()
                                        .accessDeniedHandler(accessDeniedHandler());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                e.printStackTrace();
                            }
                        }
                )
                .authenticationProvider(authenticationProvider())
                // permet de filtrer de voir si le jwt token est valide, il fera la verification avant
                // d'executer : UsernamePasswordAuthenticationFilter.class
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                //.addFilter(jwtAuthFilter)
                .cors();
        return http.build();

    }


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://locahost:5300/node"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    //un bean ne peut pas être privé ou proteger
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {

        return new CustomAccessDeniedHandler();
    }
}
