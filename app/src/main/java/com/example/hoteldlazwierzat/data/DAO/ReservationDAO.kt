package com.example.hoteldlazwierzat.data.DAO

import androidx.room.*
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.Entities.Reservation

@Dao
interface ReservationDAO {
    @Insert
    suspend fun insert(reservation: Reservation)

    @Transaction
    @Query("SELECT * FROM reservations_table WHERE ClientId = :clientId")
    fun getAllReservationsOfClient(clientId: Int): List<Reservation>
}