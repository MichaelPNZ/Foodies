package com.example.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.User
import com.example.domain.usecases.user_db_use_case.GetAllUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val saveUser: SaveUserUseCase,
    private val getAllUser: GetAllUserUseCase,
) : ViewModel() {

    fun saveIsLoginStatus(id: String) {
        viewModelScope.launch {
            val userList = getAllUser()
            val user = userList?.find { it.id == id }
            if (user != null) {
                val otherUsers = userList - user
                saveUser(user.copy(isLogin = true))
                otherUsers.forEach {
                    saveUser(it.copy(isLogin = false))
                }
            }
        }
    }

    fun saveUser(id: String) {
        viewModelScope.launch {
            val user = getAllUser()?.find { it.id == id }
            if (user == null) {
                saveUser(
                    User(
                        id = id,
                        favoriteProductList = emptyList(),
                        shoppingCartList = emptyList(),
                        isLogin = true
                    )
                )
            }
        }
    }
}