package com.timothy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.timothy.iline.data.firebaseApi.FirebaseApi
import com.timothy.iline.data.local_storage.preferences.readUser
import com.timothy.iline.domain.modal.User
import com.timothy.iline.formatTime
import com.timothy.iline.lastSeen

open class BaseActivity: AppCompatActivity() {
    protected lateinit var user: User
    protected val api = FirebaseApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = readUser()
    }

    override fun onResume() {
        super.onResume()
        api.updateLastSeen(user,"online"){exception ->  }
    }

    override fun onPause() {
        super.onPause()
        api.updateLastSeen(user,"last seen ${lastSeen(System.currentTimeMillis())}"){}
    }
}