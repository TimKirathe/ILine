package com.timothy.iline.domain.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey var phone:String = "",
    var name: String = "",
    var active:Boolean = false,
    var joined: Long = 0,
    var deviceId:String = "",
    @ColumnInfo(name = "profilePhoto") var imageUrl: String? = ""
)

