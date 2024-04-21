package com.example.foodies.data.mapper

import com.example.domain.model.User
import com.example.foodies.data.local.entity.UserDBO

fun UserDBO.toUser() = User(
    id = id,
    favoriteProductList = favoriteProductList,
    shoppingCartList = shoppingCartList,
    isLogin = isLogin,
)