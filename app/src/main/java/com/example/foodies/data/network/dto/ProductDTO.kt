package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("category_id")
    val categoryId: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("price_current")
    val priceCurrent: Int? = null,

    @SerializedName("price_old")
    val priceOld: Int? = null,

    @SerializedName("measure")
    val measure: Int? = null,

    @SerializedName("measure_unit")
    val measureUnit: String? = null,

    @SerializedName("energy_per_100_grams")
    val energyPer100Grams: Double? = null,

    @SerializedName("proteins_per_100_grams")
    val proteinsPer100Grams: Double? = null,

    @SerializedName("fats_per_100_grams")
    val fatsPer100Grams: Double? = null,

    @SerializedName("carbohydrates_per_100_grams")
    val carbohydratesPer100Grams: Double? = null,

    @SerializedName("tag_ids")
    val tagIds: List<Int>? = null
)