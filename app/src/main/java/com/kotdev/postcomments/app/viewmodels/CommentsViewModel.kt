package com.kotdev.postcomments.app.viewmodels

import androidx.lifecycle.*
import com.kotdev.postcomments.app.models.Comments
import com.kotdev.postcomments.app.network.MainApi
import com.kotdev.postcomments.helpers.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CommentsViewModel @Inject constructor(private val mainApi: MainApi) : ViewModel() {

    fun addComment(comment: Comments) = liveData(Dispatchers.IO) {
        try {
            emit(Resource.Success(data = mainApi.savePost(
                comment.name.toString(),
                comment.email.toString(),
                comment.body.toString()
            )))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun observeComments(id: Int) = liveData(Dispatchers.IO) {
        try {
            emit(Resource.Success(data = mainApi.getComments(id)))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}