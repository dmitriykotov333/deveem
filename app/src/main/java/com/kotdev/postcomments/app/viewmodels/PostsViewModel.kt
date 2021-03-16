package com.kotdev.postcomments.app.viewmodels

import androidx.lifecycle.*
import com.kotdev.postcomments.app.network.MainApi
import com.kotdev.postcomments.helpers.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class PostsViewModel @Inject constructor(private val mainApi: MainApi) : ViewModel() {

    fun observePosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(data = null))
        try {
            emit(Resource.Success(data = mainApi.getPosts()))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}