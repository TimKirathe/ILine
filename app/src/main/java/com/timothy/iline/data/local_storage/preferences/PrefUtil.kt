package com.timothy.iline.data.local_storage.preferences

import android.content.Context
import com.timothy.iline.domain.modal.User

fun Context.writeUser(user: User){
    val pref = this.getSharedPreferences("IlinePref", Context.MODE_PRIVATE)
    pref.edit()
        .putString("name", user.name)
        .putString("phone", user.phone)
        .putString("image", user.imageUrl)
        .commit()
}

fun Context.readUser():User{
    val pref = this.getSharedPreferences("IlinePref", Context.MODE_PRIVATE)
    return User(
        name = pref.getString("name","") ?: "",
        phone = pref.getString("phone", "") ?: "",
        imageUrl = pref.getString("image", "") ?: "",
        active = true,
        joined = 0L,
        deviceId = ""
    )
}
