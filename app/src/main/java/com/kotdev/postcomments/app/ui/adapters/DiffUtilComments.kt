package com.kotdev.postcomments.app.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.kotdev.postcomments.app.models.Comments

class DiffUtilComments(
    private val oldComments: List<Comments>,
    private val newComments: List<Comments>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
       return oldComments.size
    }

    override fun getNewListSize(): Int {
        return newComments.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldComments[oldItemPosition] == newComments[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldComments[oldItemPosition].postId == newComments[newItemPosition].postId
                && oldComments[oldItemPosition].id == newComments[newItemPosition].id
                && oldComments[oldItemPosition].name == newComments[newItemPosition].name
                && oldComments[oldItemPosition].email == newComments[newItemPosition].email
                && oldComments[oldItemPosition].body == newComments[newItemPosition].body
    }
}