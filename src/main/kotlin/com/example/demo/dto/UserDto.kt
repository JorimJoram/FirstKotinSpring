package com.example.demo.dto

import lombok.Data

@Data
data class UserDto(
    var id:String?,
    var pw:String?,
    var name:String?,
    var email:String?,
    var gender:Int?,
    var tel:String?,
    var birth:String?,
    var createDate:String?,
    var modifyDate:String?,
    var role:Int?
)