package com.kotdev.postcomments.app.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotdev.postcomments.R
import com.kotdev.postcomments.app.models.Comments
import com.kotdev.postcomments.helpers.ItemAnimation

class CommentsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var comments: MutableList<Comments> = arrayListOf()
    private var oldList: MutableList<Comments> = arrayListOf()

    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> Header(from(parent.context)
                .inflate(R.layout.layout_header, parent, false))
            ITEM_VIEW_TYPE_ITEM -> CommentsViewHolder(
                from(parent.context)
                    .inflate(R.layout.layout_comment_list_item, parent, false))
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
        /*val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_comment_list_item, parent, false)
        return CommentsViewHolder(view)*/
    }

    fun setPosts(comment: MutableList<Comments>) {
        val commentsDiffUtil = DiffUtilComments(comments, comment)
        val commentsDiffUtilResult = DiffUtil.calculateDiff(commentsDiffUtil)
        comments.clear()
        comments.addAll(comment)
        commentsDiffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //(holder as CommentsViewHolder).bind(comments[position])

        when (holder) {
            is CommentsViewHolder -> holder.bind(comments[position])
            is Header -> { }
            else -> throw IllegalArgumentException()
        }

        ItemAnimation.setAnimation(holder.itemView, R.anim.layout_animation)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    private fun getList() = comments

    fun addComment(comment: Comments) {
        oldList.clear()
        oldList.addAll(getList())
        val commentsDiffUtil = DiffUtilComments(oldList, comments)
        val commentsDiffUtilResult = DiffUtil.calculateDiff(commentsDiffUtil)
        comments.clear()
        comments.addAll(oldList)
        comments.add(comment)
        Log.d("Adapter", "oldList: ${oldList.size}")
        Log.d("Adapter", "comments: ${comments.size}")
        commentsDiffUtilResult.dispatchUpdatesTo(this)
    }


    class CommentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var body: TextView = itemView.findViewById(R.id.body)
        var email: TextView = itemView.findViewById(R.id.email)
        var name: TextView = itemView.findViewById(R.id.name)

        @SuppressLint("SetTextI18n")
        fun bind(comments: Comments) {
            email.text = comments.email
            name.text = comments.name
            body.text = comments.body
        }
    }

}