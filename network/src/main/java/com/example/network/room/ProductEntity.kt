package com.example.network.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common.model.Products
import com.example.common.model.ProductsItem

@Entity
data class ProductEntity(
    @PrimaryKey
    val products: Products,
)

@Entity
data class SingleProduct(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val image : String,
    val title : String
)