package com.harsh.rebelfoodsassignment.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity
import com.harsh.rebelfoodsassignment.databinding.UsersItemViewLayoutBinding

class UsersAdapter(private val users: ArrayList<UsersEntity>) : RecyclerView.Adapter<UsersItemViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersItemViewHolder =
        UsersItemViewHolder(
            UsersItemViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    fun appendData(posts: List<UsersEntity>) {
        this.users.clear()
        this.users.addAll(posts)
        notifyDataSetChanged()
    }
}