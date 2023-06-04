package com.example.hoteldlazwierzat.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.Repositories.ReservationRepo

class BookingViewModel(app: Application) : AndroidViewModel(app) {
    val reservationRepo = ReservationRepo(app.applicationContext)
    var loggedClient : Client? = null
}