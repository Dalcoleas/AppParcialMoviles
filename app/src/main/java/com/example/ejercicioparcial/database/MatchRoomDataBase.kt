package com.example.ejercicioparcial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ejercicioparcial.database.dao.MatchDao
import com.example.ejercicioparcial.database.entities.Match

@Database(entities = [Match::class], version = 1)
public abstract class MatchRoomDataBase : RoomDatabase() {
    abstract fun matchDao() : MatchDao

    companion object{
        @Volatile
        private var INSTANCE : MatchRoomDataBase? = null

        fun getDatabase(context : Context): MatchRoomDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchRoomDataBase::class.java,
                    "Match_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}