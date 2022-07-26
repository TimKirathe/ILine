package com.timothy.iline.presentation.chats

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.timothy.BaseActivity
import com.timothy.iline.data.firebaseApi.FirebaseApi
import com.timothy.iline.data.local_storage.preferences.readUser
import com.timothy.iline.databinding.ActivityChatBinding
import com.timothy.iline.domain.modal.Message
import com.timothy.iline.domain.modal.User
import com.timothy.iline.presentation.adapters.MessagesAdapter
import com.timothy.iline.whenFormat


private const val TAG = "ChatActivity"
class ChatActivity : BaseActivity() {

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
        val friend = intent.getSerializableExtra(EXTRA_FRIEND) as User
        binding.name.text = friend.name
        binding.lastSeen.text = friend.lastSeen

        val user = readUser()
        val api = FirebaseApi()

        //set up recycler view
        FirebaseApi.result.observe(this){result->
            binding.loader.visibility = View.GONE
            val messagesAdapter = MessagesAdapter(result.messages.sortedByDescending { it.timeStamp }.groupBy { whenFormat(it.timeStamp) }.flatMap {mapElement->
                val messageList = mapElement.value.map{it as Any}.toMutableList()
                messageList.add(mapElement.key)
                messageList
            }, user.phone)
            binding.messages.layoutManager = LinearLayoutManager(this)
            (binding.messages.layoutManager as LinearLayoutManager).reverseLayout = true
            binding.messages.adapter = messagesAdapter

        }
        //send message
        //update messages

        binding.send.setOnClickListener {
            val msg = binding.edtMsg.text.toString()
            if(msg.isEmpty()){
                Toast.makeText(this, "Write something please", Toast.LENGTH_SHORT).show()
            }else{
                api.sendMessage(
                    Message(body = msg, timeStamp = System.currentTimeMillis(), receiver = friend.phone, sender = user.phone)
                )
                binding.edtMsg.text.clear()
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}