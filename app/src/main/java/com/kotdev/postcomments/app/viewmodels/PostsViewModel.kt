package com.kotdev.postcomments.app.viewmodels

import androidx.lifecycle.*
import com.kotdev.postcomments.app.models.Post
import com.kotdev.postcomments.app.network.MainApi
import com.kotdev.postcomments.helpers.Resource
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class PostsViewModel @Inject constructor(private val mainApi: MainApi) : ViewModel() {

    private var posts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    private lateinit var disposable: Disposable

    fun observePosts(): LiveData<Resource<List<Post>>> {
        disposable = mainApi.getPosts()
            .onErrorReturn {
                val post = Post()
                post.id = -1
                post.userId = -1
                post.title = "error"
                post.body = "error"
                return@onErrorReturn listOf(post)
            }
            .map(Function<List<Post>, Resource<List<Post>>> { posts ->
                if (posts.isNotEmpty()) {
                    if (posts[0].id == -1) {
                        return@Function Resource.Error("Something went wrong", posts)
                    }
                }
                return@Function Resource.Success(posts)
            }).subscribeOn(Schedulers.io())
            .subscribe {
                posts.postValue(it)
            }
        return posts
    }

    fun detach() {
        disposable.dispose()
    }

}