package com.example.network

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.common.model.Products
import com.example.network.room.CashDao
import com.example.network.room.ProductEntity
import com.example.network.room.SingleProduct
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class ProductRepoImpl(val productInterface: ProductInterface, val productDao: CashDao) :
    ProductRepository {

    override fun getProduct(): Call<Products> {
        return productInterface.getProduct()
    }

    override suspend fun insertProduct(productEntity: ProductEntity) {
        productDao.insertProducts(productEntity)
    }

    override fun readProduct(): LiveData<List<ProductEntity>> {
        return productDao.getProducts()
    }

    override suspend fun saveSingleProduct(singleProduct: SingleProduct) {
        productDao.savesingleProduct(singleProduct)
    }

    override fun readSingleProduct(): LiveData<SingleProduct> {
       return productDao.readsingleProduct()
    }

}