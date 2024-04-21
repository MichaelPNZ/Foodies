package com.example.presentation.personal_account_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ShoppingCart
import com.example.domain.model.User
import com.example.domain.usecases.user_db_use_case.GetIsLoginUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalAccountViewModel @Inject constructor(
    private val getIsLoginUserUseCase: GetIsLoginUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            while (true) {
                val user = getIsLoginUserUseCase()
                user.let { _user.value = it }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            val user = getIsLoginUserUseCase()
            if (user != null) {
                saveUserUseCase(user.copy(isLogin = false))
            }
        }
    }

    fun getShoppingList(id: Int) : List<ShoppingCart> {
        return _user.value?.shoppingCartList?.get(id) ?: emptyList()
    }
}