package com.example.homeactivity

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.NetworkHelper
import com.example.common.model.Products
import com.example.network.ProductRepository
import com.example.network.room.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel(){

    val readProductRoom : LiveData<List<ProductEntity>> = productRepository.readProduct()
    private val _liveProduct = MutableLiveData<NetworkHelper<Products>>()
    val liveProduct : LiveData<NetworkHelper<Products>>
    get() = _liveProduct

    private val _liveTimer = MutableLiveData<String>()
    val liveTimer : LiveData<String>
    get() = _liveTimer

    private val _finished = MutableLiveData<Boolean>()
    val finished : LiveData<Boolean>
    get() = _finished


    var  timer : CountDownTimer? = null

    fun startTimer (){
      val timer =   object : CountDownTimer(6000,1000){
            override fun onTick(millisUntilFinished: Long) {
                _liveTimer.value = millisUntilFinished.toInt().toString()
            }

            override fun onFinish() {
                _finished.value = true
            }

        }.start()
    }
    fun getProducts(){
        _liveProduct.value = NetworkHelper.Loading()
        productRepository.getProduct().enqueue(object : Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                Log.d("PRDS","${response.code()}, ${response.body()}")

                when{
                    response.isSuccessful ->{
                        _liveProduct.value = NetworkHelper.Success(response.body()!!)
                        val data = _liveProduct.value!!.data
                        if (data != null){
                            insertProduct(data)
                        }
                    }

                    response.code() == 404 || response.code() == 400 ->{
                        _liveProduct.value = NetworkHelper.Error("${response.code()}")
                    }
                     response.code() == 500 ->{
                        _liveProduct.value = NetworkHelper.Error("${response.code()}")
                    }
                      response.code() == 403 ->{
                        _liveProduct.value = NetworkHelper.Error("${response.code()}")
                    }
                      response.code() == 403 ->{
                        _liveProduct.value = NetworkHelper.Error("${response.code()}")
                    }
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {

                Log.d("PRDSOO","${t.message}, ${t.localizedMessage}")

            }

        })
    }

    fun insertProduct(products: Products) {
        viewModelScope.launch(Dispatchers.IO){
            val productEntity = ProductEntity(products)
            productRepository.insertProduct(productEntity)
        }
    }

}