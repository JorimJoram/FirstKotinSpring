package com.example.demo.dto

import java.time.LocalDateTime

data class Person(var name:String,
                  var age:Int,
                  var createDate: LocalDateTime)