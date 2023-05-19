package com.example.hoteldlazwierzat.data.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "clients_table")
class Client(@PrimaryKey(autoGenerate = true)val id:Int = 0,
             username:String,
             name:String = "DEFAULT",
             surname:String = "DEFAULT",
             email:String = "DEFAULT",
             password:String,
             var phoneNumber:String = "DEFAULT",
             var address:String = "DEFAULT",
             var city:String = "DEFAULT",
             var postalCode:String = "DEFAULT",
             var province:String = "DEFAULT",
             ) : User(username,name,surname,email,password) {

    override fun LogOut() {
        TODO("Not yet implemented")
    }
}