package com.example.demo

import com.example.demo.dto.Person
import com.example.demo.dto.PersonRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class PersonUnitTest(
    @Autowired private val personRepository: PersonRepository
) {
    @Test
    fun createPerson(){
        this.personRepository.save(Person(null,"Jorim", 26, LocalDateTime.now()))
    }

    @Test
    fun readAllPerson(){
        println(this.personRepository.findAll())
        println(this.personRepository.findAll()[0])
        println(this.personRepository.findAll()[0].id)
    }

    @Test
    fun readByIdPerson(){
        println(this.personRepository.findById(1).orElse(null))
    }

    @Test
    fun readByNamePerson(){
        println(this.personRepository.findByName("Jorim"))
        println(this.personRepository.findByName("Joram"))
        println(this.personRepository.findByName("Joram").isEmpty())
    }

    @Test
    fun updatePerson(){
        val oData = this.personRepository.findById(1).get()
        oData.age = 27
        this.personRepository.save(oData) //select -> update 순차적으로 수행
        println("AFTER DATA UPDATE")
        println(this.personRepository.findById(1))//다시 select 쿼리 수행
    }

    @Test
    fun deletePerson(){
        val oData = this.personRepository.findById(1).get()
        println(this.personRepository.delete(oData))
    }
}