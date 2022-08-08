package com.timothy.iline.data.api

import com.timothy.iline.domain.modal.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface api {

    @GET("user")
    suspend fun getAllUsers():List<User>

    @POST("user")
    suspend fun saveUser(@Body user: User)
}