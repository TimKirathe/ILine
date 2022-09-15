package com.timothy.iline.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timothy.iline.R
import com.timothy.iline.domain.modal.Message

class MessagesAdapter(val messages:List<Message>, val number:String) : RecyclerView.Adapter<MessagesAdapter.MyViewHolder>() {

    open class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val msgBody: TextView = itemView.findViewById(R.id.body)
        private val time: TextView = itemView.findViewById(R.id.time)

        fun bind(message:Message){
            msgBody.text = message.body
            time.text = message.timeStamp.toString()
        }
    }

    class OwnMessageViewHolder(itemView: View): MyViewHolder(itemView)

    class OtherMessageViewHolder(itemView: View): MyViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        when(messages[position].sender){
            number -> return 0
            else -> return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(if (viewType ==0)R.layout.my_bubbles else R.layout.friend_bubbles,parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

}