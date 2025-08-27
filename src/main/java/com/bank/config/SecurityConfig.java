package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // If you have a custom UserDetailsService, expose it as a @Bean elsewhere and inject it here.
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
              // Admin-only sections
              .requestMatchers("/roles/**").hasRole("ADMIN")
              .requestMatchers("/branches/form", "/branches/save", "/branches/edit/**", "/branches/delete/**").hasRole("ADMIN")
              .requestMatchers("/users/list", "/users/delete/**", "/users/new").hasRole("ADMIN")

              // Mixed or user-facing pages
              .requestMatchers("/", "/home").authenticated()
              .requestMatchers("/branches/list").hasAnyRole("ADMIN","USER")
              .requestMatchers("/users/form", "/users/save", "/users/edit/**").hasAnyRole("ADMIN","USER")

              // everything else needs auth
              .anyRequest().authenticated()
          )
          .formLogin(login -> login
              .loginPage("/login").permitAll()
              .defaultSuccessUrl("/", true)
          )
          .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll())
          .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
            "/css/**", "/js/**", "/images/**", "/webjars/**"
        );
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }
}
