package com.example.github.user.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.github.user.R
import com.example.github.user.database.DatabaseFollowing

class FollowingAdapter (val context: Context):
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    // variable for our all followers list
    private val allFollowing = ArrayList<DatabaseFollowing>()

    // create a view holder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // initializing all our variables which we have added in layout file
        val login: TextView = itemView.findViewById<TextView>(R.id.followUsername)
        val imgFollowing: ImageView = itemView.findViewById<ImageView>(R.id.followPic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_follow, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set data to item of recycler view
        holder.login.text = allFollowing[position].login

        val imgUri = allFollowing[position].avatar_url.toUri().buildUpon().build()
        Glide.with(holder.imgFollowing)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .circleCrop()
            ).into(holder.imgFollowing)
    }

    override fun getItemCount(): Int {
        // returning our list size
        return allFollowing.size
    }

    // method to update our list of followers
    fun updateFollowingList(newList: List<DatabaseFollowing>) {
        // clear our notes array list
        allFollowing.clear()

        // add new list to our all followers list
        allFollowing.addAll(newList)

        // notify our adapter
        notifyDataSetChanged()
    }
}