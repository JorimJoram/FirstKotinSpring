package com.example.demo.config

import com.example.demo.dao.AccountDao
import com.example.demo.exception.InvalidRoleException
import lombok.RequiredArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CustomUserDetailService(private val accountDao: AccountDao): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val userDto = accountDao.findByUsername(username).orElseThrow{UsernameNotFoundException("user not found")}
        val authorities = ArrayList<GrantedAuthority>()
        when(userDto.role){
            0 -> authorities.add(SimpleGrantedAuthority("ADMIN"))
            1 -> authorities.add(SimpleGrantedAuthority("USER"))
            2 -> authorities.add(SimpleGrantedAuthority("TRAINER"))
            else -> throw InvalidRoleException("InvalidRole")
        }
        return User(userDto.id, userDto.pw, authorities)
    }
}