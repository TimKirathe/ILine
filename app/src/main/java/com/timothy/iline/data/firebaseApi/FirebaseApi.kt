package com.timothy.iline.data.firebaseApi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.timothy.iline.domain.modal.Message
import com.timothy.iline.domain.modal.User

private const val MESSAGE_COLLECTION = "Messages"
private const val USER_COLLECTION = "Users"
private const val TAG = "Firebase Error"
class FirebaseApi {
    private val db = Firebase.firestore

    init {
        db.collection(USER_COLLECTION).addSnapshotListener{value, error->
            error?.let {
                Log.e(TAG, "An error has occurred: ",error )
            }
            value?.let {
                val temp = mutableListOf<User>()
                for (doc in value.documents){
                    val user = doc.toObject(User::class.java)
                    user?.let {
                        temp.add(user)
                    }
                }
                result.value = result.value?.copy(
                    users = temp
                )
            }
        }
        db.collection(MESSAGE_COLLECTION).addSnapshotListener{ value, error->
            error?.let {
                Log.e(TAG, "An error has occurred: ",error )
            }
            value?.let {
                val temp = mutableListOf<Message>()
                for (doc in value.documents){
                    val message = doc.toObject(Message::class.java)
                    message?.let {
                        temp.add(message)
                    }
                }
                result.value = result.value?.copy(
                    messages = temp
                )
            }
        }
    }

    fun AddUser(user: User){
        db.collection(USER_COLLECTION).document(user.name).set(user)
            .addOnSuccessListener {
                Log.i(TAG, "AddUser: User added",)
            }
            .addOnFailureListener {
                Log.e(TAG, "AddUser Exception", it)
            }
    }
    fun sendMessage(message: Message){
        db.collection(MESSAGE_COLLECTION).add(message)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    companion object{
        val result = MutableLiveData(Result())
    }

}

data class Result(
    val messages:List<Message> = emptyList(),
    val users:MutableList<User> = mutableListOf(),
    val firebaseError:FirebaseFirestoreException? = null
)