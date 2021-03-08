package com.kotdev.postcomments.app.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Comments (
    @SerializedName("postId")
    @Expose
    var postId: Int = 0,
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("body")
    @Expose
    var body: String? = null)
