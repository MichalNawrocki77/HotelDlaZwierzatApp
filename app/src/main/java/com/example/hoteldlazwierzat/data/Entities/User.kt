package com.example.hoteldlazwierzat.data.Entities

import androidx.room.PrimaryKey

abstract class User(var username:String,
                    var name:String="DEFAULT",
                    var surname:String="DEFAULT",
                    var email:String="DEFAULT",
                    var password:String){
    override fun toString() = "name: $name; username: $username; email: $email;surname: $surname;"
}