package com.example.hoteldlazwierzat

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.hoteldlazwierzat.ViewModels.BookingViewModel
import com.example.hoteldlazwierzat.data.Entities.Reservation
import com.example.hoteldlazwierzat.databinding.ActivityBookingBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private val bookingVM by viewModels<BookingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val editTextDate: TextInputEditText = findViewById(R.id.date)

        editTextDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                editTextDate.setText(formattedDate)
            }, year, month, day)

            datePickerDialog.show()
        }
        binding.btnSend.setOnClickListener() {
            if(!binding.name.text.isNullOrBlank() &&
               !binding.phone.text.isNullOrBlank() &&
               !binding.email.text.isNullOrBlank() &&
               !binding.date.text.isNullOrBlank()) {
                val reservation = Reservation(
                    clientId = bookingVM.loggedClient?.id,
                    clientName = binding.name.text.toString(),
                    clientPhoneNumber = binding.phone.text.toString(),
                    clientEmail = binding.email.text.toString(),
                    date = LocalDate.parse(binding.date.text.toString(), DateTimeFormatter.ofPattern("dd/MM/uuuu")))

                CoroutineScope(Dispatchers.IO).launch{
                    bookingVM.reservationRepo.insert(reservation)
                    withContext(Dispatchers.Main){
                        Toast.makeText(applicationContext,"Reservation registered successfully",Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(applicationContext,"Please fill in all information",Toast.LENGTH_SHORT).show()
            }
        }
    }
}