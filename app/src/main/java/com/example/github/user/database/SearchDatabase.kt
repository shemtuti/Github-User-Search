package com.example.github.user.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseUser::class, DatabaseFollower::class, DatabaseFollowing::class], version = 1)
abstract class SearchDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}

// singleton pattern to get an instance of the database.
private lateinit var INSTANCE: SearchDatabase

fun getDatabase(context: Context): SearchDatabase {
    synchronized(SearchDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SearchDatabase::class.java,
                "search_github_db"
            ).build()

        }
    }
    return INSTANCE
}