package com.timothy.iline.domain.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val phone:String,
    val name:String,
    @ColumnInfo(name = "profilePhoto") val profile_photo:String,
)

