package com.timothy.iline.data.local_storage.room.daos

import androidx.room.*
import com.timothy.iline.domain.modal.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface message_dao {

    @Query("SELECT * FROM message_table")
    fun getAllMessage():List<Message>

//    @Query("SELECT * FROM message_table WHERE timeStamp LIKE :timeStamp")
//    fun getByTimeStamp(timeStamp:String):Message

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Delete
    fun deleteMessage(message: Message)
}