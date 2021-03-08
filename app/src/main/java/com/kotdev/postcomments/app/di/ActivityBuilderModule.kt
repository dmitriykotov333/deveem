package com.kotdev.postcomments.app.di

import com.kotdev.postcomments.app.ui.activies.MainActivity
import com.kotdev.postcomments.app.di.main.MainFragmentBuildersModule
import com.kotdev.postcomments.app.di.main.MainModule
import com.kotdev.postcomments.app.di.main.MainScope
import com.kotdev.postcomments.app.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {


    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

}
