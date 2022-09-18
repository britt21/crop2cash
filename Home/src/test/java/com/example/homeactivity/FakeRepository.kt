package com.example.homeactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.model.Products
import com.example.network.ProductRepository
import com.example.network.room.ProductEntity
import com.example.network.room.SingleProduct
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val call : Call<Products>? =null
val productEntitymock = mutableListOf<ProductEntity>()
val singleproductEntitymock = mutableListOf<SingleProduct>()
val observableEnityList = MutableLiveData<List<ProductEntity>>(productEntitymock)
val singleobservableEnityList = MutableLiveData<SingleProduct>()
class FakeRepository : ProductRepository {

    override fun getProduct(): Call<Products> {
        return mockCall()
    }


    override suspend fun insertProduct(productEntity: ProductEntity) {
        productEntitymock.add(productEntity)
        refreshLiveData()
    }

    private fun refreshLiveData(){
        return observableEnityList.postValue(productEntitymock)

    }
    override fun readProduct(): LiveData<List<ProductEntity>> {
        return observableEnityList
    }

    override suspend fun saveSingleProduct(singleProduct: SingleProduct) {
        singleproductEntitymock.add(singleProduct)
    }

    override fun readSingleProduct(): LiveData<SingleProduct> {

        return singleobservableEnityList
    }
}

class mockCall : Call<Products>{
    override fun clone(): Call<Products> {
        return call!!
    }

    override fun execute(): Response<Products> {
        return call!!.execute()
    }

    override fun enqueue(callback: Callback<Products>) {}

    override fun isExecuted(): Boolean {
        return false
    }

    override fun cancel() {}

    override fun isCanceled(): Boolean {return false}

    override fun request(): Request? {return null
    }

    override fun timeout(): Timeout? {
        return null
    }

}