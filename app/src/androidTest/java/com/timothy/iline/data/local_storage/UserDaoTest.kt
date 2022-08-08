package com.timothy.iline.data.local_storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.timothy.iline.data.local_storage.room.IlineDB
import com.timothy.iline.data.local_storage.room.daos.user_dao
import com.timothy.iline.domain.modal.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRules = InstantTaskExecutorRule()

    lateinit var database:IlineDB
    lateinit var dao: user_dao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            IlineDB::class.java
        ).allowMainThreadQueries().build()

        dao = database.userDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertUser(): Unit = runBlocking {
        val user = User(phone = "Phone",name="name", imageUrl = "", active = false, joined = 0L, deviceId = "")
        dao.insertUser(user)

        val allUsers = dao.getAllUsers()
        assertThat(allUsers.contains(user))
    }

    @Test
    fun deleteUserTest():Unit = runBlocking {
        val user1 = User(phone = "Phone",name="name", imageUrl = "", active = false, joined = 0L, deviceId = "")
        val user2 = User(phone = "Phone",name="name", imageUrl = "", active = false, joined = 0L, deviceId = "")
        dao.insertUser(user1)
        dao.insertUser(user2)
        dao.deleteUSer(user1)

        val allUsers = dao.getAllUsers()
        assertThat(allUsers).doesNotContain(user1)
    }
}