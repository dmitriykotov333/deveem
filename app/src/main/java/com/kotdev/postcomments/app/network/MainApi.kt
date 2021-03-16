package com.kotdev.postcomments.app.network

import com.kotdev.postcomments.app.models.Comments
import com.kotdev.postcomments.app.models.Post
import retrofit2.http.*


interface MainApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("comments")
    suspend fun getComments(@Query("postId") id : Int): List<Comments>

    @POST("comments")
    @FormUrlEncoded
    suspend fun savePost(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("body") body: String
    ): Comments
}