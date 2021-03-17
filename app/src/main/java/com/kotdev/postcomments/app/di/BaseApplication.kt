package com.kotdev.postcomments.app.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application()