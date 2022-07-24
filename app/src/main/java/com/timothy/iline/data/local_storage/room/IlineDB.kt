package com.timothy.iline.data.local_storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timothy.iline.data.local_storage.room.daos.message_dao
import com.timothy.iline.data.local_storage.room.daos.user_dao
import com.timothy.iline.domain.modal.Message
import com.timothy.iline.domain.modal.User

@Database(entities = [User::class, Message::class], version = 1)
abstract class IlineDB:RoomDatabase() {
    abstract fun userDao():user_dao
    abstract fun messageDao():message_dao
}