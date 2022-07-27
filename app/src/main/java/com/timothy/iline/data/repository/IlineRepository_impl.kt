package com.timothy.iline.data.repository

import android.content.Context
import androidx.room.Room
import com.timothy.iline.data.local_storage.room.IlineDB
import com.timothy.iline.domain.modal.Message
import com.timothy.iline.domain.modal.User

class IlineRepository_impl(context: Context): IlineRepository {

    val db = Room.databaseBuilder(
        context,
        IlineDB::class.java,
        "IlineDB"
    ).build()

    override fun getAllMessages(): List<Message> {
        return db.messageDao().getAllMessage()
    }

    override fun insertMessage(message: Message) {
        db.messageDao().insertMessage(message)
    }

    override fun deleteMessage(message: Message) {
        db.messageDao().deleteMessage(message)
    }

    override fun getAllFriends(): List<User> {
        return db.userDao().getAllUsers()
    }

    override fun addFriend(user: User) {
        db.userDao().insertUser(user)
    }

    override fun deleteFriend(user: User) {
        db.userDao().deleteUSer(user)
    }

    override fun getFriendByNumber(number: String):User {
        return db.userDao().getUserByPhoneNumber(number)
    }
}