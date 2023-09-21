package dev.tferdous.familycashcardapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Value("${spring.data.rest.base-path}")
    private String baseUrl;

    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/cashcards/**")
                            .authenticated();
                }).csrf(c -> c.disable())
                .build();
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers(HttpMethod.GET, this.baseUrl + "/**").permitAll()
//                            .requestMatchers(HttpMethod.POST, this.baseUrl).hasAuthority("ROLE_admin")
//                            .requestMatchers(HttpMethod.PUT, this.baseUrl + "/**").hasAuthority("ROLE_admin")
//                            .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/**").hasAuthority("ROLE_admin")
//                            .anyRequest().authenticated();
//                })
//                .csrf(AbstractHttpConfigurer::disable)
    }

     @Bean
     public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
     }

}
