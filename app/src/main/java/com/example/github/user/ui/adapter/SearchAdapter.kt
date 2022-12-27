package com.example.github.user.ui.adapter

import android.content.Context
import android.media.Image
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
import com.example.github.user.database.DatabaseFollower
import timber.log.Timber

class SearchAdapter (val context: Context):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    // variable for our all followers list
    private val allFollowers = ArrayList<DatabaseFollower>()

    // create a view holder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // initializing all our variables which we have added in layout file
        val login: TextView = itemView.findViewById<TextView>(R.id.followUsername)
        val imgFollower: ImageView = itemView.findViewById<ImageView>(R.id.followPic)
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
        //holder.login.text = allFollowers[position].login

        val imgUri = allFollowers[position].avatar_url.toUri().buildUpon().build()
        Glide.with(holder.imgFollower.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .circleCrop()
            ).into(holder.imgFollower)
    }

    override fun getItemCount(): Int {
        // returning our list size
        return allFollowers.size

        Timber.i("DATA-Followers-Size: $allFollowers.size")
    }

    // method to update our list of followers
    fun updateFollowersList(newList: List<DatabaseFollower>) {
        // clear our notes array list
        allFollowers.clear()

        // add new list to our all followers list
        allFollowers.addAll(newList)

        // notify our adapter
        notifyDataSetChanged()
    }
}