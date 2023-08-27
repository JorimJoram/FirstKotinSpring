package com.example.demo.config

import lombok.RequiredArgsConstructor
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@RequiredArgsConstructor
class CustomAuthSuccessHandler: SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        defaultTargetUrl = "/success"
        super.onAuthenticationSuccess(request, response, authentication)
    }
}