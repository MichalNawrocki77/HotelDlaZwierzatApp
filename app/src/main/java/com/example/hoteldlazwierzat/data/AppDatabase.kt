package com.example.hoteldlazwierzat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hoteldlazwierzat.data.DAO.ClientDAO
import com.example.hoteldlazwierzat.data.Entities.Client

@Database(entities = [Client::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientDAO() : ClientDAO

    companion object{
        private var Instance : AppDatabase? = null

        fun getInstance(context:Context): AppDatabase{
            if(Instance==null){
                Instance = Room.databaseBuilder(context,AppDatabase::class.java,"HotelDlaZwierzat-database").build()
            }
            return Instance!!
        }
    }
}