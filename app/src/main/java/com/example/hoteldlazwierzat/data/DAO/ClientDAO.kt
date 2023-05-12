package com.example.hoteldlazwierzat.data.DAO

import androidx.room.*
import com.example.hoteldlazwierzat.data.Entities.Client
import com.example.hoteldlazwierzat.data.Entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDAO {
    @Insert
    suspend fun insert(client: Client)

    @Delete
    suspend fun delete(client: Client)

    @Update
    suspend fun update(client: Client)

    /*@Query("SELECT * FROM clients_table" +
            "WHERE id=:clientId")
    fun findClientById(clientId: Int) : Client

    @Query("SELECT * FROM clients_table" +
            "WHERE login LIKE :clientLogin")
    fun findClientByLogin(clientLogin: String) : Client*/
}