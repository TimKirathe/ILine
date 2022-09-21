package com.timothy.iline.data.local_storage.preferences

import android.content.Context
import android.content.SharedPreferences
import com.timothy.iline.domain.modal.User

private const val MYPREFS = "MyPreferences"


fun Context.writeUser(user: User){
    val pref = this.getSharedPreferences(MYPREFS, Context.MODE_PRIVATE)
    pref.edit()
        .putString("name", user.name)
        .putString("phone", user.phone)
        .putString("image", user.imageUrl)
        .putString("lastSeen", user.lastSeen)
        .apply()
}

fun Context.readUser():User{
    val pref = this.getSharedPreferences(MYPREFS, Context.MODE_PRIVATE)
    return User(
        name = pref.getString("name","") ?: "",
        phone = pref.getString("phone", "") ?: "",
        imageUrl = pref.getString("image", "") ?: "",
        active = true,
        joined = 0L,
        deviceId = "",
        lastSeen = pref.getString("lastSeen", "") ?: "",
    )
}
