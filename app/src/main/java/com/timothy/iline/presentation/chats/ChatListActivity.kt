package com.timothy.iline.presentation.chats

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.timothy.BaseActivity
import com.timothy.iline.MainActivity
import com.timothy.iline.R
import com.timothy.iline.data.api.RetrofitInstance
import com.timothy.iline.data.firebaseApi.FirebaseApi
import com.timothy.iline.data.local_storage.preferences.readUser
import com.timothy.iline.data.local_storage.preferences.writeUser
import com.timothy.iline.domain.modal.User
import com.timothy.iline.presentation.adapters.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val EXTRA_FRIEND = "EXTRA_FRIEND"
class ChatListActivity : BaseActivity() {
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
            try {
//                val users = RetrofitInstance.api.getAllUsers().filter { it.phone != readUser().phone }
                    FirebaseApi.result.observe(this@ChatListActivity){result->
                        val usersList = result.users.filter { it.phone != readUser().phone }
                        val userAdapter = UserAdapter(this@ChatListActivity, R.layout.user_item, usersList)
                        dialog = AlertDialog.Builder(this@ChatListActivity)
                            .setAdapter(userAdapter) { dialog, position ->
                                dialog.dismiss()
                                enterInChat(usersList[position])
                            }
                    }
            }catch (e:Exception){
                error.text = e.message
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
                dialog.show()
            }
            R.id.logout -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun enterInChat(user: User) {
        val intent = Intent(this@ChatListActivity, ChatActivity::class.java)
//        intent.putExtra(EXTRA_FRIEND, receiver)
        intent.putExtra(EXTRA_FRIEND, user)
        startActivity(intent)
    }

    private fun logout() {
        writeUser(User("","",false,0L,"",""))
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}