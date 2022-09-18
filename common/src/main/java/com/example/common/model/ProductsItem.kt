package com.example.common.model

import androidx.room.Entity

data class ProductsItem(
    val images: List<String> = listOf("Iphones"),
    val title: String = "Mega"
)