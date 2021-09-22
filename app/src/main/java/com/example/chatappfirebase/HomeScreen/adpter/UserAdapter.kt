package com.example.chatappfirebase.HomeScreen.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatappfirebase.HomeScreen.model.User
import com.example.chatappfirebase.R
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewModel>() {

    private var data: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        return ViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewModel, position: Int) = holder.bind(data[position])

    fun swapData(data: List<User>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: User) = with(itemView) {

            val txUserName: TextView = itemView.findViewById(R.id.userName)
            val userImage: CircleImageView = itemView.findViewById(R.id.userImage)
            val txtTemp: TextView = itemView.findViewById(R.id.temp)

            txUserName.text = item.userName
            Glide.with(context).load(item.profileImage).into(userImage)

            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}