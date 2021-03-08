package com.kotdev.postcomments.app.network

import com.kotdev.postcomments.app.models.Comments
import com.kotdev.postcomments.app.models.Post
import io.reactivex.Flowable
import retrofit2.http.*


interface MainApi {

    @GET("posts")
    fun getPosts(): Flowable<List<Post>>

    @GET("comments")
    fun getComments(@Query("postId") id : Int): Flowable<List<Comments>>

    @POST("comments")
    @FormUrlEncoded
    fun savePost(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("body") body: String
    ): Flowable<Comments>
}