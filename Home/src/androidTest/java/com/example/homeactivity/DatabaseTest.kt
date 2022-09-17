package com.example.homeactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.common.model.Products
import com.example.network.room.CashDao
import com.example.network.room.CashDatabase
import com.example.network.room.ProductEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CashDatabase
    private lateinit var dao: CashDao



    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CashDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.cashDao
    }
    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun InsertProductPasssProductIsPopulated() {
        runTest {
            val product = Products()
            val productEntity = ProductEntity(product)
            dao.insertProducts(productEntity)


            dao.getProducts().getOrAwaitValue()
            val value = dao.getProducts().getOrAwaitValue()
            assertThat(value).isNotEmpty()

        }
    }
}