package com.example.domain.model

data class Catalog(
    val categoryList: List<Category>,
    val productList: List<Product>,
    val tagList: List<Tag>
)