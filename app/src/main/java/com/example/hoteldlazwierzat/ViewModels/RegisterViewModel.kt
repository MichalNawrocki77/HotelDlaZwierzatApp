package com.example.hoteldlazwierzat.ViewModels

import android.app.Application
import android.provider.ContactsContract.Intents.Insert
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.Repositories.ClientRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RegisterViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ClientRepo(app.applicationContext)


    suspend fun SuspendedInsertClient(client: Client){
        repo.insert(client)
    }

    fun GetClientByUserName(username:String) : Client {
        return repo.findClientByUserName(username)
    }
    fun getAllClientUsernames() : List<String>{
        return repo.getAllClientUsernames()
    }
}