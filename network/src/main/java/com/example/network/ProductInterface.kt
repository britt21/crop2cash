package com.example.network

import com.example.common.model.Products
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val CROP_BASE = "https://my-json-server.typicode.com/"

interface ProductInterface {

    @GET("Reyst/exhibit_db/list")
    fun getProduct(): Call<Products>
}

