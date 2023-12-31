package com.example.demo

import com.example.demo.dto.Person
import com.example.demo.service.AccountService

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime

@Controller

class MainController(private val accountService: AccountService) {

    @GetMapping("/index")
    fun mainPage(model: Model): String {
        val user = accountService.findByUsername("test")
        model.addAttribute("user", user.get())
        return "test"
    }

    @GetMapping("/login")
    fun loginPage(): String {
        return "login"
    }

    @GetMapping("/success")
    fun successLoginPage(): String {
        return "success"
    }
}