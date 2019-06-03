package com.example.ejercicioparcial.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.ejercicioparcial.database.entities.Match
import com.example.ejercicioparcial.database.dao.MatchDao

class MatchRepository(private val matchDao : MatchDao) {

    val allMatches : LiveData<List<Match>> = matchDao.getAllMatches()

    @WorkerThread
    suspend fun insert(match: Match){
        matchDao.insert(match)
    }
}