package com.kotdev.postcomments.app.ui.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kotdev.postcomments.R
import com.kotdev.postcomments.app.models.Post
import com.kotdev.postcomments.helpers.ItemAnimation
import java.util.*


class PostRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posts: MutableList<Post> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    fun setPosts(post: List<Post>) {
        posts.clear()
        posts.addAll(post)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(posts[position])
        ItemAnimation.setAnimation(holder.itemView, R.anim.recycler)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var userId: TextView = itemView.findViewById(R.id.userId)
        private var id: TextView = itemView.findViewById(R.id.id)
        private var title: TextView = itemView.findViewById(R.id.title)
        private var body: TextView = itemView.findViewById(R.id.body)
        private var comments: TextView = itemView.findViewById(R.id.comments)

        @SuppressLint("SetTextI18n")
        fun bind(post: Post) {
            userId.text = "User ID ${post.userId}"
            id.text = "ID ${post.id}"
            title.text = post.title
            body.text = post.body

            comments.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", post.userId)
                itemView.findNavController().navigate(R.id.commentsPostFragment, bundle)
            }
        }
    }
}