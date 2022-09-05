package com.timothy.iline.presentation.chats

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.timothy.iline.databinding.ActivityChatBinding
import com.timothy.iline.domain.modal.User


private const val TAG = "ChatActivity"
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)
        //setup toolbar
        //get the intent extras
        val receiver = intent.getSerializableExtra(EXTRA_FRIEND) as User
        binding.name.text = receiver.name

        //set up recycler view
        //send message
        //update messages

        binding.send.setOnClickListener {

        }

    }
}