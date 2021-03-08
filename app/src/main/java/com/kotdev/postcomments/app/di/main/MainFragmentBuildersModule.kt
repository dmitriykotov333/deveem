package com.kotdev.postcomments.app.di.main

import com.kotdev.postcomments.app.ui.fragments.CommentsPostFragment
import com.kotdev.postcomments.app.ui.fragments.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): PostsFragment

    @ContributesAndroidInjector
    abstract fun contributeCommentsPostFragment(): CommentsPostFragment

}