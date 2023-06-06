package com.example.hoteldlazwierzat.data

import android.content.Context
import androidx.room.*
import com.example.hoteldlazwierzat.data.DAO.ClientDAO
import com.example.hoteldlazwierzat.data.DAO.ReservationDAO
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.Entities.LocalDateConverter
import com.example.hoteldlazwierzat.data.Entities.Reservation

@Database(
    entities = [Client::class,Reservation::class],
    version = 1,
)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientDAO() : ClientDAO
    abstract fun reservationDAO() : ReservationDAO

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