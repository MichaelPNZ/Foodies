package com.example.domain.repository

import com.example.domain.model.User

interface UserDBRepository {

    suspend fun saveUser(user: User)
    suspend fun getUserById(id: String): User?
    suspend fun getIsLoginUser(): User?
    suspend fun getAllUsers(): List<User>?
}