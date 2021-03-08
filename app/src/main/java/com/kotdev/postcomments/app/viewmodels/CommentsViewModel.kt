package com.kotdev.postcomments.app.viewmodels

import androidx.lifecycle.*
import com.kotdev.postcomments.app.models.Comments
import com.kotdev.postcomments.app.network.MainApi
import com.kotdev.postcomments.helpers.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class CommentsViewModel @Inject constructor(private val mainApi: MainApi) : ViewModel() {

    private var comments: MediatorLiveData<Resource<List<Comments>>> = MediatorLiveData()

    fun addComment(comment: Comments): io.reactivex.Flowable<Resource.Success<Comments>> {
        return mainApi.savePost(
            comment.name.toString(),
            comment.email.toString(),
            comment.body.toString()
        ).map(Function { comments ->
            return@Function Resource.Success(comments)
        }).subscribeOn(Schedulers.io())
    }

    fun observeComments(id: Int): LiveData<Resource<List<Comments>>> {
        val source = LiveDataReactiveStreams.fromPublisher(
            mainApi.getComments(id)
                .onErrorReturn {
                    val comments = Comments()
                    comments.id = -1
                    return@onErrorReturn listOf(comments)
                }
                .map(Function<List<Comments>, Resource<List<Comments>>> { comments ->
                    if (comments.isNotEmpty()) {
                        if (comments[0].id == -1) {
                            return@Function Resource.Error("Something went wrong", comments)
                        }
                    }
                    return@Function Resource.Success(comments)
                }).subscribeOn(Schedulers.io())
        )

        comments.addSource(source) {
            comments.value = it
            comments.removeSource(source)
        }
        return comments
    }

}