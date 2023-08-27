package com.example.demo.service

import com.example.demo.dao.AccountDao
import com.example.demo.dto.UserDto
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.Optional

@RequiredArgsConstructor
@Service
class AccountService(private val accountDao:AccountDao) {
    fun findByUsername(id:String) : Optional<UserDto>{
        return accountDao.findByUsername(id);
    }
}