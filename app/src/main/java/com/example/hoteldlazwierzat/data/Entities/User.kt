package com.example.hoteldlazwierzat.data.Entities

import androidx.room.PrimaryKey

abstract class User(var username:String,
                    var name:String="DEFAULT",
                    var surname:String="DEFAULT",
                    var email:String="DEFAULT",
                    var password:String){
    abstract fun LogOut()
    //overriding of toString is for database testing purposes only!
    override fun toString() = "name: $name; surname: $surname; email: $email; password: $password"
}