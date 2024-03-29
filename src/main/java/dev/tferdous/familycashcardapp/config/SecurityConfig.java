package dev.tferdous.familycashcardapp.config;

import dev.tferdous.familycashcardapp.model.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static dev.tferdous.familycashcardapp.model.enums.Role.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.data.rest.base-path}")
    private String baseUrl;

    private final String registerUrl = "api/cashcards/v1/auth/register";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth-> {
            auth.requestMatchers(HttpMethod.GET, this.registerUrl).hasAnyRole(USER.name(), ADMIN.name())
                    .requestMatchers(HttpMethod.GET, this.registerUrl + "/**").hasAnyRole(USER.name(), ADMIN.name())
                    .requestMatchers(HttpMethod.POST, this.registerUrl).hasAnyRole(USER.name(), ADMIN.name())
                    .requestMatchers(HttpMethod.PUT, this.baseUrl + "/**").hasAnyRole(USER.name(), ADMIN.name())
                    .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/**").hasAnyRole(USER.name(), ADMIN.name());
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails janeDoe = User.builder()
                .username("jane_doe")
                .password(passwordEncoder.encode("oranges456"))
                .roles("UNREGISTERED")
                .build();

        UserDetails johnDoe = User.builder()
                .username("john_doe")
                .password(passwordEncoder.encode("apples123"))
                .roles("CARD-OWNER")
                .build();

        UserDetails blake2 = User.builder()
                .username("blake2")
                .password(passwordEncoder.encode("blake123"))
                .roles("CARD-OWNER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(johnDoe, blake2, janeDoe, admin);
    }

}
