package com.timothy.iline.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timothy.iline.R
import com.timothy.iline.domain.modal.Message
import com.timothy.iline.formatTime

class MessagesAdapter(val messages:List<Any>, val number:String) : RecyclerView.Adapter<MessagesAdapter.MyViewHolder>() {

    open class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bind(message:Any){
            if(message is String){
                val timeline = itemView.findViewById<TextView>(R.id.timeline)
                timeline.text = message
            }else {
                val msgBody: TextView = itemView.findViewById(R.id.body)
                val time: TextView = itemView.findViewById(R.id.time)
                msgBody.text = (message as Message).body
                time.text = formatTime(message.timeStamp)
            }
        }
    }

    class OwnMessageViewHolder(itemView: View): MyViewHolder(itemView)

    class OtherMessageViewHolder(itemView: View): MyViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (messages[position] is String){
            return 2
        }else{
            when((messages[position] as Message).sender){
                number -> return 0
                else -> return 1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (viewType == 0)
                R.layout.my_bubbles
            else if(viewType == 1)
                R.layout.friend_bubbles
            else
                R.layout.timeline,parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

}