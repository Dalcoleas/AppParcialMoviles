package com.example.ejercicioparcial.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicioparcial.database.entities.Match
import com.example.ejercicioparcial.repository.MatchRepository
import com.example.ejercicioparcial.database.MatchRoomDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : MatchRepository

    val allMatches : LiveData<List<Match>>

    init {
        val matchesDao = MatchRoomDataBase.getDatabase(application).matchDao()
        repository = MatchRepository(matchesDao)
        allMatches = repository.allMatches
    }

    fun insert(match: Match) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(match)
    }

}