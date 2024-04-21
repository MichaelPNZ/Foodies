package com.example.foodies.data.repository

import com.example.domain.model.User
import com.example.domain.repository.UserDBRepository
import com.example.foodies.data.local.dao.UserDao
import com.example.foodies.data.local.entity.UserDBO
import com.example.foodies.data.mapper.toUser
import javax.inject.Inject

class UserDBRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserDBRepository {

    override suspend fun saveUser(user: User) {
        userDao.saveUser(
            UserDBO(
                id = user.id,
                favoriteProductList = user.favoriteProductList,
                shoppingCartList = user.shoppingCartList,
                isLogin = user.isLogin
            )
        )
    }

    override suspend fun getUserById(id: String): User? {
        return userDao.getUserById(id)?.toUser()
    }

    override suspend fun getIsLoginUser(): User? {
        return userDao.getIsLoginUser()?.toUser()
    }

    override suspend fun getAllUsers(): List<User>? {
        return userDao.getAllUsers()?.map { it.toUser() }
    }
}