package com.example.everytranslation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.everytranslation.db.dao.MessageDao
import com.example.everytranslation.db.dao.RoomDao
import com.example.everytranslation.db.dao.UserDao
import com.example.everytranslation.db.dto.ChatRoom
import com.example.everytranslation.db.dto.Message
import com.example.everytranslation.db.dto.User

@Database(entities = [ChatRoom::class, Message::class, User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao() : MessageDao
    abstract fun roomDao() : RoomDao
    abstract fun userDao() : UserDao

    companion object {
        private var instance : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "MY_DB")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance as AppDatabase
        }
    }
}