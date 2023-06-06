package com.example.hoteldlazwierzat.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hoteldlazwierzat.data.Repositories.ReservationRepo

class PreviewViewModel(app: Application) : AndroidViewModel(app) {
    val reservationRepo = ReservationRepo(app.applicationContext)

    fun GetAllReservationsOfLoggedClient() = reservationRepo.getAllReservationsOfClient(LoggedClient.client!!.id)
}