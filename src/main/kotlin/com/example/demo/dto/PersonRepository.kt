package com.example.demo.dto

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PersonRepository:JpaRepository<Person, Long> {
    override fun findById(id:Long): Optional<Person>
    fun findByName(name:String): List<Person>
}