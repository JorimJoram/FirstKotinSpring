package com.example.demo.dao

import com.example.demo.dto.UserDto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.Optional

@Repository

class AccountDao(@Autowired val jdbcTemplate: JdbcTemplate) {
    fun findByUsername(id: String?): Optional<UserDto> {
        val sql = "SELECT * FROM USER_INFO WHERE USER_ID = ?"

        var userDto: UserDto?
        try {
            userDto = jdbcTemplate.queryForObject(sql, RowMapper<UserDto> { rs: ResultSet, _: Int ->
                UserDto(
                    rs.getString("USER_ID"),
                    rs.getString("USER_PW"),
                    rs.getString("USER_NAME"),
                    rs.getString("USER_EMAIL"),
                    rs.getInt("USER_GENDER"),
                    rs.getString("USER_TEL"),
                    rs.getString("USER_BIRTH"),
                    rs.getString("USER_C_DT"),
                    rs.getString("USER_M_DT") ?: "",
                    rs.getInt("USER_ROLE")
                )
            }, id)
        }catch (e:Exception){
            userDto = null
        }

        return Optional.ofNullable(userDto)
    }
}