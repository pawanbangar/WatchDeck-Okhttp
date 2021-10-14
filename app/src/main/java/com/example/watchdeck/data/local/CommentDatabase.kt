package com.example.watchdeck.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.watchdeck.data.entities.Comment

@Database(entities = [Comment::class], version = 1, exportSchema = false)
abstract class CommentDatabase : RoomDatabase() {

    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile private var instance: CommentDatabase? = null

        fun getCommentDatabase(context: Context): CommentDatabase =
            instance ?: synchronized(this) { instance ?: buildCommentDatabase(context).also { instance = it } }

        private fun buildCommentDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, CommentDatabase::class.java, "comments")
                .fallbackToDestructiveMigration()
                .build()
    }

}