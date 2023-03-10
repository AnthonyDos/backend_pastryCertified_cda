package com.pastrycertified.cda.config;

import com.pastrycertified.cda.handlers.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Configuration des accès routes et des autorisations
 */
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
                                                "/**/register",
                                                "**/addresses",
                                                "/**/authenticate-admin",
                                                "/**/shop/all-shops/",
                                                "/**/shop/all-shops/{shop-id}",
                                                "**/products/",
                                                "**/products/all-products",
                                                "**/products/{product-id}",
                                                "/**/categories/all-categories",
                                                "/**/categories/{category-id}",
                                                /**
                                                 * resources swagger
                                                 */
                                                "/v2/api-docs",
                                                "/v3/api-docs",
                                                "/v3/api-docs/**",
                                                "/swagger-resources",
                                                "/swagger-resources/**",
                                                "/configuration/ui",
                                                "/configuration/security",
                                                "/swagger-ui/**",
                                                "/webjars/**",
                                                "/swagger-ui.html"
                                )
                                        .permitAll()
                                        .antMatchers(
                                                "/**/users/",
                                                "/**/users/**",
                                                "/**/orders/id",
                                                "/**/orders/",
                                                "/**/orders/user/{id}",
                                                "**/invoices/all-invoices/{user-id}",
                                                "**/invoices/invoice/{invoice-id}").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                                        .antMatchers(
                                                "/**/register-admin",
                                                "/**/register-pastrychef",
                                                "/**/shop/**",
                                                "/**/categories/**",
                                                "/**/options/**",
                                                "/**/orders/**",
                                                "**/employees/**"
                                                ).access("hasRole('ROLE_ADMIN')")//authorisé ces url
                                        .antMatchers(
                                                "/**/employees/{employee-id}",
                                                "/**/employees/update/{employee-id}",
                                                "/**/orders/**").access("hasRole('PASTRY_CHEF') or hasRole('ROLE_ADMIN')")
                                        .anyRequest()//toutes les autres requêtes doivent être authentifiés
                                        .authenticated()
                                        .and()
                                        .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .exceptionHandling()
                                        .accessDeniedHandler(accessDeniedHandler());
                            } catch (Exception e) {
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
