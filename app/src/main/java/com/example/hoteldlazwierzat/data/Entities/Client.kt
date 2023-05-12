package com.example.hoteldlazwierzat.data.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "clients_table")
class Client(@PrimaryKey(autoGenerate = true)val id:Int = 0,
             name:String,
             surname:String,
             email:String,
             password:String,
             var phoneNumber:String = "DEFAULT",
             var address:String = "DEFAULT",
             var city:String = "DEFAULT",
             var postalCode:String = "DEFAULT",
             var province:String = "DEFAULT",
             ) : User(name,surname,email,password) {
    override fun Login() {
        TODO("Not yet implemented")
    }

    override fun Register() {
        TODO("Not yet implemented")
    }

    override fun LogOut() {
        TODO("Not yet implemented")
    }
}