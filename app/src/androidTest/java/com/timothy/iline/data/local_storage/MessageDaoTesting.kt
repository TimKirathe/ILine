package com.timothy.iline.data.local_storage

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.timothy.iline.data.local_storage.room.IlineDB
import com.timothy.iline.data.local_storage.room.daos.message_dao
import com.timothy.iline.domain.modal.Message
import com.timothy.iline.domain.modal.MessageStatus
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MessageDaoTesting {

    private lateinit var database: IlineDB
    private lateinit var dao:message_dao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            IlineDB::class.java
        ).allowMainThreadQueries().build()

        dao = database.messageDao()
    }


    @After
    fun tearDown(){
        database.close()
    }


    @Test
    fun insertMessage(): Unit = runBlocking {
        val message = Message(id=1,sender="sender", receiver = "receiver", body = "body", timeStamp = 0L, image_url = "", status = MessageStatus.Draft)
        dao.insertMessage(message)

        val allMessages = dao.getAllMessage()

        assertThat(allMessages.contains(message))
    }

    @Test
    fun deleteMessage() = runBlocking {  }
}