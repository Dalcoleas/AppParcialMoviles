package com.example.ejercicioparcial.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_table")
data class Match (
    @ColumnInfo(name = "team_a")
    val teamA:String,

    @ColumnInfo(name = "team_b")
    val teamB:String,

    @ColumnInfo(name = "score_a")
    val scoreA: Int,

    @ColumnInfo(name = "score_b")
    val scoreB: Int,

    @ColumnInfo(name = "date")
    val date: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}