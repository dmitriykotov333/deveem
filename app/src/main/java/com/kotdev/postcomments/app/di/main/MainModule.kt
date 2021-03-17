package com.kotdev.postcomments.app.di.main

import androidx.recyclerview.widget.ConcatAdapter
import com.kotdev.postcomments.app.network.MainApi
import com.kotdev.postcomments.app.ui.adapters.CommentsRecyclerAdapter
import com.kotdev.postcomments.app.ui.adapters.HeaderAdapter
import com.kotdev.postcomments.app.ui.adapters.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(FragmentComponent::class)
@Module
class MainModule {

    @FragmentScoped
    @Provides
    fun provideAdapter() = PostRecyclerAdapter()

    @FragmentScoped
    @Provides
    fun provideHeaderAdapter() = HeaderAdapter()

    @FragmentScoped
    @Provides
    fun provideConcatAdapter(headerAdapter: HeaderAdapter, postRecyclerAdapter: PostRecyclerAdapter): ConcatAdapter {
        return ConcatAdapter(headerAdapter, postRecyclerAdapter)
    }

    @FragmentScoped
    @Provides
    fun provideCommentsRecyclerAdapter() = CommentsRecyclerAdapter()
}