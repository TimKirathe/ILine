package com.timothy.iline.presentation.chats

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timothy.iline.R
import com.timothy.iline.data.api.RetrofitInstance
import com.timothy.iline.data.local_storage.preferences.readUser
import com.timothy.iline.presentation.adapters.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatListActivity : AppCompatActivity() {
    lateinit var dialog: AlertDialog.Builder
    lateinit var chatList:RecyclerView
    lateinit var progress:ProgressBar
    lateinit var noChats: LinearLayout
    lateinit var error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        chatList = findViewById(R.id.chatView)
        progress = findViewById(R.id.progress)
        noChats = findViewById(R.id.noChats)
        error = findViewById(R.id.error)

        CoroutineScope(Dispatchers.Main).launch {
            val users = RetrofitInstance.api.getAllUsers()
            val userAdapter = UserAdapter(this@ChatListActivity, R.layout.user_item, users)
            dialog = AlertDialog.Builder(this@ChatListActivity)
                .setAdapter(userAdapter
                ) { dialog, position ->
                    enterInChat(readUser().phone, users[position].phone)
                }
        }





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.editButton -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun enterInChat(sender: String, receiver: String) {
        val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
//        intent.putExtra(EXTRA_SENDER, sender)
//        intent.putExtra(EXTRA_RECEIVER, receiver)
        startActivity(intent)
    }
}