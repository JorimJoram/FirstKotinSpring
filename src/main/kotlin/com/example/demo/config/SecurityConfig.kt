package com.example.demo.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customAuthSuccessHandler: CustomAuthSuccessHandler,
    private val customAuthFailureHandler: CustomAuthFailureHandler
) {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
        //Exception 던질 수 있다는 throws를 사용하지 않음
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
        http.authorizeHttpRequests().antMatchers("/", "/login").permitAll()
        http.authorizeHttpRequests().antMatchers("/success").hasAuthority("ADMIN").anyRequest().authenticated()
        http.formLogin().loginPage("/login").loginProcessingUrl("/login/action")
            .successHandler(customAuthSuccessHandler)
            .failureHandler(customAuthFailureHandler)
        http.logout()
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .logoutUrl("/logout").permitAll()
            .logoutSuccessUrl("/index")
        return http.build()
    }
}