package com.example.watchdeck.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watchdeck.data.entities.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments where id=:id")
    fun getComments(id:Int): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comment: List<Comment>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: Comment)
}