package ru.netology.nmedia.repository

import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAllAsync(callback: Callback<List<Post>>)
    fun saveAsync(post: Post, callback: Callback<Post>)
    fun removeByIdAsync(id: Long, callback: Callback<Unit>)
    fun likeByIdAsync(id: Long, callback: Callback<Post>)
    fun unlikeByIdAsync(id: Long, callback: Callback<Post>)

    interface Callback<T> {
        fun onSuccess(value: T)
        fun onError(e: Exception)
    }
}
