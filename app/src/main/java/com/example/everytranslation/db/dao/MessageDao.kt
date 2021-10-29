package com.example.everytranslation.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.everytranslation.db.dto.Message

@Dao
interface MessageDao {
    @Query("SELECT * FROM MESSAGE WHERE room_id== :roomId")
    fun getAll(roomId : Long) : LiveData<List<Message>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: Message)

    @Update
    fun update(message: Message)

    @Delete
    fun delete(message: Message)

    @Query("DELETE FROM MESSAGE")
    fun deleteAll()
}