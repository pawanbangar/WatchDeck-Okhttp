package com.example.watchdeck.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "issues")
class Data(
    @SerializedName("info")
    val info: String? = null,
    @SerializedName("id")
    @PrimaryKey
    val id:Int?=null
)