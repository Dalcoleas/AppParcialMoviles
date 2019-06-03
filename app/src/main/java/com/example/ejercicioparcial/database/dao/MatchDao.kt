package com.example.ejercicioparcial.database.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ejercicioparcial.database.entities.Match

@Dao
interface MatchDao {

    @Insert
    suspend fun insert(match: Match)

    @Query("DELETE FROM match_table")
    fun deleteAll()

    @Query("SELECT * FROM match_table")
    fun getAllMatches(): LiveData<List<Match>>
}