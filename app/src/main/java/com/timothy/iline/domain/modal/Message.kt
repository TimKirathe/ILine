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
    @PrimaryKey(autoGenerate = true) var id:Int? = null,
    var sender:String = "",
    var receiver:String = "",
    var body:String = "",
    var timeStamp:Long = 0L,
    var image_url:String = "",
    var status: MessageStatus = MessageStatus.Sent
)
