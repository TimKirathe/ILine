package com.timothy.iline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.timothy.iline.data.api.RetrofitInstance
import com.timothy.iline.data.local_storage.preferences.readUser
import com.timothy.iline.data.local_storage.preferences.writeUser
import com.timothy.iline.domain.modal.User
import com.timothy.iline.presentation.chats.ChatListActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(readUser().name.isNotEmpty()){
            startActivity(Intent(this, ChatListActivity::class.java))
            finish()
        }

        val name_ed = findViewById<EditText>(R.id.name)
        val phone_ed = findViewById<EditText>(R.id.number)
        val submit_btn = findViewById<Button>(R.id.submit)

        submit_btn.setOnClickListener {
            if(name_ed.text.isEmpty() || phone_ed.text.isEmpty()){
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }else{
                try {
                    val user = User(
                        name = name_ed.text.toString(),
                        phone = phone_ed.text.toString(),
                        imageUrl = "",
                        active = false,
                        joined = 0L,
                        deviceId = ""
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        RetrofitInstance.api.saveUser(user)
                    }
                    writeUser(user)
                }catch (e:Exception){
                    Log.e("Server Error", "onCreate: ",e)
                }
            }
        }

    }
}