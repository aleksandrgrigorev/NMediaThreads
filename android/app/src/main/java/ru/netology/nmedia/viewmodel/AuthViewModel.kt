package ru.netology.nmedia.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.netology.nmedia.auth.AppAuth
import ru.netology.nmedia.error.AppError
import ru.netology.nmedia.model.FeedModelState
import ru.netology.nmedia.util.SingleLiveEvent

class AuthViewModel: ViewModel() {
    val state = AppAuth.getInstance().state
        .asLiveData()
    val authorized: Boolean
        get() = state.value != null

    private val _error = SingleLiveEvent<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    fun updateUser(login: String, password: String) =
        viewModelScope.launch {
            try {
                AppAuth.getInstance().update(login, password)
            } catch (e: Exception) {
                println(e)
                throw AppError.from(e)
            }
        }
}