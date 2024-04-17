package com.example.foodies.data.mapper

import com.example.domain.model.Catalog
import com.example.foodies.data.local.entity.CatalogDBO

fun CatalogDBO.toCatalog(): Catalog {
    return Catalog(
        categoryList = categoryList,
        productList = productList,
        tagList = tagList
    )
}