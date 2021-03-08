package com.kotdev.postcomments.app.di.main

import androidx.lifecycle.ViewModel
import com.kotdev.postcomments.app.di.ViewModelKey
import com.kotdev.postcomments.app.viewmodels.CommentsViewModel
import com.kotdev.postcomments.app.viewmodels.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    abstract fun bindPostsCommentsViewModel(commentsViewModel: CommentsViewModel?): ViewModel?

}