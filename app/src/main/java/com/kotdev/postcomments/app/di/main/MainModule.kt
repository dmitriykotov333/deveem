package com.kotdev.postcomments.app.di.main

import androidx.recyclerview.widget.ConcatAdapter
import com.kotdev.postcomments.app.network.MainApi
import com.kotdev.postcomments.app.ui.adapters.CommentsRecyclerAdapter
import com.kotdev.postcomments.app.ui.adapters.HeaderAdapter
import com.kotdev.postcomments.app.ui.adapters.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @Provides
    fun provideAdapter() = PostRecyclerAdapter()

    @MainScope
    @Provides
    fun provideHeaderAdapter() = HeaderAdapter()

    @MainScope
    @Provides
    fun provideConcatAdapter(headerAdapter: HeaderAdapter, postRecyclerAdapter: PostRecyclerAdapter): ConcatAdapter {
        return ConcatAdapter(headerAdapter, postRecyclerAdapter)
    }

    @MainScope
    @Provides
    fun provideCommentsRecyclerAdapter() = CommentsRecyclerAdapter()
}