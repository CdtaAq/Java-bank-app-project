// SAVE: banking-project/src/main/java/com/bank/config/SecurityConfig.java
package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(req -> req
              .requestMatchers("/css/**","/js/**","/images/**","/","/h2/**").permitAll()
              .anyRequest().authenticated()
          )
          .formLogin(Customizer.withDefaults())
          .logout(l -> l.logoutSuccessUrl("/").permitAll());

        // H2 console frames
        http.headers(h -> h.frameOptions(f -> f.disable()));
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**"));

        return http.build();
    }

    // Quick start users; replace with DB-backed later
    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin").password("{noop}admin").roles("ADMIN").build(),
            User.withUsername("manager").password("{noop}manager").roles("MANAGER").build(),
            User.withUsername("user").password("{noop}user").roles("USER").build()
        );
    }
}
