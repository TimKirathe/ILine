package com.timothy.iline.data.local_storage.room.daos

import androidx.room.*
import com.timothy.iline.domain.modal.User


@Dao
interface user_dao {

    @Query("SELECT * FROM user_table")
    fun getAllUsers():List<User>

    @Query("SELECT * FROM user_table WHERE phone LIKE :number")
    fun getUserByPhoneNumber(number:String):User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun deleteUSer(user: User)

}

