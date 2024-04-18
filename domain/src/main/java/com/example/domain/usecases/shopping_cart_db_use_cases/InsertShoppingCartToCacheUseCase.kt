package com.example.domain.usecases.shopping_cart_db_use_cases

import com.example.domain.model.ShoppingCart
import com.example.domain.repository.ShoppingCartDBRepository
import javax.inject.Inject

class InsertShoppingCartToCacheUseCase @Inject constructor(
    private val shoppingCartDBRepository: ShoppingCartDBRepository
) {
    suspend operator fun invoke(shoppingCart: List<ShoppingCart>) = shoppingCartDBRepository.insertShoppingCart(shoppingCart)
}