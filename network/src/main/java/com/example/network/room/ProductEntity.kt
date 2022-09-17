package com.example.network.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common.model.Products

@Entity
data class ProductEntity(

    @PrimaryKey
    val products: Products

)