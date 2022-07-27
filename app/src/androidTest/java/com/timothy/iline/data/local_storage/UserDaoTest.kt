package com.timothy.iline.data.local_storage

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.timothy.iline.data.local_storage.room.IlineDB
import com.timothy.iline.data.local_storage.room.daos.user_dao
import com.timothy.iline.domain.modal.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserDaoTest {

    lateinit var database:IlineDB
    lateinit var dao: user_dao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            IlineDB::class.java
        ).build()

        dao = database.userDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun InsertUser() = runBlocking {
        val user = User(phone = "Phone",name="name", profile_photo = "")
        dao.insertUser(user)

        val allUsers = dao.getAllUsers()
        assertThat(allUsers.contains(user))
    }
}