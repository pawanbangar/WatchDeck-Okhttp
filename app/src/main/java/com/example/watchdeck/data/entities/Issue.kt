package com.example.watchdeck.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "issues")
data class Issue(
    @SerializedName("title")
    val title: String? = null,
    @PrimaryKey
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("body")
    val body:String?=null,
    @Embedded
    val user:User?=null,
    @SerializedName("updated_at")
    val updated_at: String?=null,
    @SerializedName("comments_url")
    val comments_url:String?=null
)