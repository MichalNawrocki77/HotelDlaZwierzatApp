package com.example.hoteldlazwierzat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.allViews
import com.example.hoteldlazwierzat.ViewModels.LoggedClient
import com.example.hoteldlazwierzat.ViewModels.PreviewViewModel
import com.example.hoteldlazwierzat.data.Entities.Reservation
import com.example.hoteldlazwierzat.databinding.ActivityPreviewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding
    private var reservationsList: List<Reservation>? = null
    private val previewVM by viewModels<PreviewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreviewBinding.inflate(layoutInflater)
        if(LoggedClient.isLoggedIn()){
            CoroutineScope(Dispatchers.IO).launch {
                reservationsList = previewVM.GetAllReservationsOfLoggedClient()
                withContext(Dispatchers.Main){
                    for(reservation in reservationsList!!){
                        AddNewReservationCard(
                            reservation.clientName,
                            reservation.clientPhoneNumber,
                            reservation.clientEmail,
                            reservation.date.toString())
                    }
                }
            }
        }
        setContentView(binding.root)
    }
    fun AddNewReservationCard(name: String, phone: String,email: String, date: String){
        val reservationCard = layoutInflater.inflate(R.layout.reservation_card,null)
        val nameText: TextView = reservationCard.findViewById(R.id.nameSurname)
        val phoneText: TextView = reservationCard.findViewById(R.id.phone)
        val emailText: TextView = reservationCard.findViewById(R.id.email)
        val dateText: TextView = reservationCard.findViewById(R.id.data)
        nameText.text = name
        phoneText.text = phone
        emailText.text = email
        dateText.text = date
        binding.reservationList.addView(reservationCard)
    }
}