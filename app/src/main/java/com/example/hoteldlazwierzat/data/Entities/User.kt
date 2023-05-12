package com.example.hoteldlazwierzat.data.Entities

import androidx.room.PrimaryKey

abstract class User(var name:String="DEFAULT",
                    var surname:String="DEFAULT",
                    var email:String="DEFAULT",
                    var password:String="DEFAULT"){
    abstract fun Login()
    abstract fun Register()
    abstract fun LogOut()
}