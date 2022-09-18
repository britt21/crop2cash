package com.example.network

import androidx.lifecycle.LiveData
import com.example.common.model.Products
import com.example.network.room.ProductEntity
import com.example.network.room.SingleProduct
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface ProductRepository {

    fun getProduct(): Call<Products>

 suspend   fun insertProduct(productEntity: ProductEntity)

    fun readProduct(): LiveData<List<ProductEntity>>

    suspend fun saveSingleProduct(singleProduct: SingleProduct)

    fun readSingleProduct(): LiveData<SingleProduct>
}