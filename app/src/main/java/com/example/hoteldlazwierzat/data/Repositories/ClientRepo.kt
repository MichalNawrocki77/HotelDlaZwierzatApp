package com.example.hoteldlazwierzat.data.Repositories

import android.content.Context
import com.example.hoteldlazwierzat.data.AppDatabase
import com.example.hoteldlazwierzat.data.DAO.ClientDAO
import com.example.hoteldlazwierzat.data.Entities.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClientRepo(context: Context) : ClientDAO {
    private val dao = AppDatabase.getInstance(context).clientDAO()

    override suspend fun insert(client: Client){
        withContext(Dispatchers.IO){
            dao.insert(client)
        }
    }

    override suspend fun delete(client: Client) {
        withContext(Dispatchers.IO) {
            dao.delete(client)
        }
    }

    override suspend fun update(client: Client) {
        withContext(Dispatchers.IO) {
            dao.update(client)
        }
    }
}