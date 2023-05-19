package com.example.hoteldlazwierzat.ViewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.Repositories.ClientRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RegisterViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ClientRepo(app.applicationContext)
    private val context = app.applicationContext

    fun InsertClient(client: Client){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.insert(client)
        }
    }
    fun UpdateClient(client: Client){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.update(client)
        }
    }
    fun DeleteClient(client: Client){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repo.delete(client)
        }
    }
}