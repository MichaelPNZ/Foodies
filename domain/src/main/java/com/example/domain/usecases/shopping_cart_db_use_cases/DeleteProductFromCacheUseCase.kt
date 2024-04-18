package com.example.domain.usecases.shopping_cart_db_use_cases

import com.example.domain.model.Product
import com.example.domain.repository.ShoppingCartDBRepository
import javax.inject.Inject

class DeleteProductFromCacheUseCase @Inject constructor(
    private val shoppingCartDBRepository: ShoppingCartDBRepository
) {

    suspend operator fun invoke(product: Product) = shoppingCartDBRepository.deleteProduct(product)
}