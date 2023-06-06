package com.example.hoteldlazwierzat.data.Repositories

import android.content.Context
import com.example.hoteldlazwierzat.data.AppDatabase
import com.example.hoteldlazwierzat.data.DAO.ReservationDAO
import com.example.hoteldlazwierzat.data.Entities.Reservation

class ReservationRepo(context: Context): ReservationDAO {
    private val dao = AppDatabase.getInstance(context).reservationDAO()
    override suspend fun insert(reservation: Reservation) {
        return dao.insert(reservation)
    }

    override fun getAllReservationsOfClient(clientId: Int): List<Reservation> {
        return dao.getAllReservationsOfClient(clientId)
    }


}