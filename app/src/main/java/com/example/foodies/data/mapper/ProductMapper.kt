package com.example.foodies.data.mapper

import com.example.data.network.dto.ProductDTO
import com.example.domain.model.Product

fun ProductDTO.toProduct(): Product {
    return Product(
        id = id ?: 0,
        categoryId = categoryId ?: 0,
        name = name ?: "",
        description = description ?: "",
        image = image ?: "",
        priceCurrent = priceCurrent ?: 0,
        priceOld = priceOld ?: 0,
        measure = measure ?: 0,
        measureUnit = measureUnit ?: "",
        energyPer100Grams = energyPer100Grams ?: 0.0,
        proteinsPer100Grams = proteinsPer100Grams ?: 0.0,
        fatsPer100Grams = fatsPer100Grams ?: 0.0,
        carbohydratesPer100Grams = carbohydratesPer100Grams ?: 0.0,
        tagIds = tagIds ?: emptyList(),
    )
}