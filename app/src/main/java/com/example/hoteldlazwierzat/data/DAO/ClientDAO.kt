package com.example.hoteldlazwierzat.data.DAO

import androidx.room.*
import com.example.hoteldlazwierzat.data.Entities.Client

@Dao
interface ClientDAO {
    @Insert
    suspend fun insert(client: Client)

    @Delete
    suspend fun delete(client: Client)

    @Update
    suspend fun update(client: Client)


    @Query("SELECT * FROM clients_table WHERE username=:username")
    fun findClientByUserName(username: String) : Client

    @Query("SELECT username FROM clients_table")
    fun getAllClientUsernames() : List<String>


/*
    @Query("SELECT * FROM clients_table" +
            "WHERE login LIKE :clientLogin")
    fun findClientByLogin(clientLogin: String) : Client
*/
}