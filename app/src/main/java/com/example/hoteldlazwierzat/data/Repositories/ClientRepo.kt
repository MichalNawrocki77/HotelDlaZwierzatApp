package com.example.hoteldlazwierzat.data.Repositories

import android.content.Context
import com.example.hoteldlazwierzat.data.AppDatabase
import com.example.hoteldlazwierzat.data.DAO.ClientDAO
import com.example.hoteldlazwierzat.data.Entities.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ClientRepo(context: Context) : ClientDAO {
    private val dao = AppDatabase.getInstance(context).clientDAO()

    override suspend fun insert(client: Client){
        dao.insert(client)
    }

    override suspend fun delete(client: Client) {
        dao.delete(client)
    }

    override suspend fun update(client: Client) {
        dao.update(client)
    }


    override fun findClientByUserName(username: String): Client {
        return dao.findClientByUserName(username)
    }

    override fun getAllClientUsernames(): List<String> {
        return dao.getAllClientUsernames()
    }


}