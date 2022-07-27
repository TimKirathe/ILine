package com.timothy.iline.data.local_storage.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.timothy.iline.domain.modal.Message

@Dao
interface message_dao {

    @Query("SELECT * FROM message_table")
    fun getAllMessage():List<Message>

//    @Query("SELECT * FROM message_table WHERE timeStamp LIKE :timeStamp")
//    fun getByTimeStamp(timeStamp:String):Message

    @Insert
    fun insertMessage(message: Message)

    @Delete
    fun deleteMessage(message: Message)
}