package com.kotdev.postcomments.app.di

import androidx.lifecycle.ViewModelProvider
import com.kotdev.postcomments.app.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}