package com.example.watchdeck.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.watchdeck.data.entities.Issue

@Database(entities = [Issue::class], version = 1, exportSchema = false)
abstract class IssueDatabase : RoomDatabase() {

    abstract fun issueDao(): IssueDao

    companion object {
        @Volatile private var instance: IssueDatabase? = null

        fun getIssueDatabase(context: Context): IssueDatabase =
            instance ?: synchronized(this) { instance ?: buildIssueDatabase(context).also { instance = it } }

        private fun buildIssueDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, IssueDatabase::class.java, "issues")
                .fallbackToDestructiveMigration()
                .build()
    }

}