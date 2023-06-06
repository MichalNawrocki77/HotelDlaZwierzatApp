package com.example.hoteldlazwierzat.ViewModels

import com.example.hoteldlazwierzat.data.Entities.Client

class LoggedClient {
    companion object{
        var client: Client? = null
        fun isLoggedIn() = client != null
    }
}