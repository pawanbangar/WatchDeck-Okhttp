package com.example.watchdeck.data.local
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watchdeck.data.entities.Issue

@Dao
interface IssueDao {
    @Query("SELECT * FROM issues")
    fun getIssues() : LiveData<List<Issue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(issues: List<Issue>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(issues: Issue)

}
