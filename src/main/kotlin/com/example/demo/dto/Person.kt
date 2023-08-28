package com.example.demo.dto

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Person(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long?,
                  @Column var name:String,
                  @Column var age:Int,
                  @Column var createDate: LocalDateTime)