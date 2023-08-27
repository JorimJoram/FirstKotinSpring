package com.example.demo.config

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
@Component
class CustomAuthFailureHandler(private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()): AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        when(exception){
            is UsernameNotFoundException -> redirectStrategy.sendRedirect(request, response, "/login?errorCode=not_found")
            is BadCredentialsException -> redirectStrategy.sendRedirect(request, response, "/login?errorCode=bad_credentials")
            is AccountExpiredException -> redirectStrategy.sendRedirect(request, response, "/login?errorCode=account_expired")
            else -> redirectStrategy.sendRedirect(request, response, "/login?errorCode=unknown")
        }
    }
}