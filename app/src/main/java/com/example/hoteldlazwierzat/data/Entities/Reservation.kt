package com.example.hoteldlazwierzat.data.Entities

import java.time.LocalDate
import java.time.LocalDateTime

class Reservation(
                    val id:Int,
                    val clientId: Int,
                    val dateOfBookingReservation: LocalDateTime,
                    val startDateTimeOfReservation: LocalDateTime,
                    val endDateTimeOfReservation: LocalDateTime,
                    val cost: Double) {
}