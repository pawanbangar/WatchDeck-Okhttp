package com.example.watchdeck.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comments")
data class Comment (
        @PrimaryKey
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("body")
        val body: String? = null
)