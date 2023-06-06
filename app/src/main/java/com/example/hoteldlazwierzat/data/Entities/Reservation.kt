package com.example.hoteldlazwierzat.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "reservations_table")
data class Reservation(@PrimaryKey(autoGenerate = true)val id:Int = 0,
                       val clientId: Int?,
                       val clientName: String = "DEFAULT",
                       val clientPhoneNumber: String = "DEFAULT",
                       val clientEmail: String = "DEFAULT",
                       val date: LocalDate = LocalDate.parse("0000/00/00", DateTimeFormatter.ofPattern("dd/MM/uuuu")))
class LocalDateConverter{
    @TypeConverter
    fun DateToString(date: LocalDate) = date.toString()
    @TypeConverter
    fun StringToDate(string: String) = LocalDate.parse(string)
}