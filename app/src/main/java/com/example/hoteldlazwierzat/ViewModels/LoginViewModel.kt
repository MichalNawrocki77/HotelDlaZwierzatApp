package com.example.hoteldlazwierzat.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hoteldlazwierzat.data.AppDatabase
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.Repositories.ClientRepo

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ClientRepo(app.applicationContext)
    fun GetClientByUserName(username:String) : Client? {
        return repo.findClientByUserName(username)
    }
    fun GetAllUsernames() : List<String>{
        return repo.getAllClientUsernames()
    }
}