package com.kotdev.postcomments.helpers

import com.kotdev.postcomments.app.models.Comments

interface Click {
    fun add(comment: Comments)
}