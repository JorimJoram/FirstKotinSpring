package com.example.demo.config


import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSession

@Component

class CustomAuthenticationProvider(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val customUserDetailService: CustomUserDetailService,
    private val session:HttpSession
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val username = authentication?.name
        val password = authentication?.credentials

        val user = customUserDetailService.loadUserByUsername(username)

        if(!passwordEncoder.matches(password.toString(), user.password)){
            throw BadCredentialsException("Invalid user password")
        }
        session.setAttribute("session_user", user)

        return UsernamePasswordAuthenticationToken(user, user.password, user.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}