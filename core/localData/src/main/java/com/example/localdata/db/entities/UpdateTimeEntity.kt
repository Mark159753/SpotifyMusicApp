package com.example.localdata.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "update_times")
data class UpdateTimeEntity(
    @PrimaryKey
    val key:String,
    val time:Long
)
