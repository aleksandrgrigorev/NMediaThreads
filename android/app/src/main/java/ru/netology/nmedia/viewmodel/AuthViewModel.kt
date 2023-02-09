package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.nmedia.auth.AppAuth
import ru.netology.nmedia.error.NetworkException
import ru.netology.nmedia.error.UnknownException
import java.io.IOException

class AuthViewModel: ViewModel() {
    val state = AppAuth.getInstance().state
        .asLiveData()
    val authorized: Boolean
        get() = state.value != null

    fun updateUser(login: String, password: String) =
        viewModelScope.launch {
            try {
                AppAuth.getInstance().update(login, password)
            } catch (e: IOException) {
                throw NetworkException
            } catch (e: Exception) {
                println(e)
                throw UnknownException
            }
        }
}