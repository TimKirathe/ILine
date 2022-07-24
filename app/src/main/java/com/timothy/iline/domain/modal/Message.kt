package com.timothy.iline.domain.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class MessageStatus{
    Received,
    Sent,
    Draft
}
@Entity(tableName = "message_table")
data class Message(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val sender:String,
    val receiver:String,
    val body:String,
    val timeStamp:Long,
    val image_url:String,
    val status: MessageStatus
)
