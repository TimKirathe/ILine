package com.timothy.iline.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.timothy.iline.R
import com.timothy.iline.domain.modal.User

class UserAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val values: List<User>
): ArrayAdapter<User>(context,layoutResource,values) {

    override fun getItem(position: Int): User = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context).inflate(layoutResource, parent)
        return bindData(getItem(position), view)
    }

    private fun bindData(item: User, view: View): View {
        val name_txt = view.findViewById<TextView>(R.id.name)
        val number_txt = view.findViewById<TextView>(R.id.number)
        name_txt?.text = item.name
        number_txt?.text = item.phone

        return view
    }
}