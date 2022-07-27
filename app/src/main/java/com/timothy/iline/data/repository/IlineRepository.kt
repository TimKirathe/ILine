package com.timothy.iline.data.repository

import com.timothy.iline.domain.modal.Message
import com.timothy.iline.domain.modal.User

interface IlineRepository {
    fun getAllMessages():List<Message>

    fun insertMessage(message: Message)

    fun deleteMessage(message: Message)

    fun getAllFriends():List<User>

    fun addFriend(user: User)

    fun deleteFriend(user: User)

    fun getFriendByNumber(number:String):User
}