package dev.tferdous.familycashcardapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, this.baseUrl).hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, this.baseUrl + "/**").hasRole("CARD-OWNER")
                    .requestMatchers(HttpMethod.POST, this.baseUrl).hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, this.baseUrl + "/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/**").hasRole("ADMIN");
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

     @Bean
     public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
     }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails janeDoe = User.builder()
                .username("jane_doe")
                .password(passwordEncoder().encode("oranges456"))
                .roles("UNREGISTERED")
                .build();

        UserDetails johnDoe = User.builder()
                .username("john_doe")
                .password(passwordEncoder().encode("apples123"))
                .roles("CARD-OWNER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(johnDoe, janeDoe, admin);
    }

}
