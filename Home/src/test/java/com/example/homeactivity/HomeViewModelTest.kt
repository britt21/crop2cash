package com.example.homeactivity

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.network.room.SingleProduct
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
class HomeViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcher = MainDispatcher()

    @Mock
    lateinit var mockContext: Context

    lateinit var viewModel: HomeViewModel
    lateinit var instrumentationContext: Context


    @Before
    fun setUp() {
        mockContext  =  Mockito.mock(Context::class.java)
        Mockito.`when`(mockContext.applicationContext).thenReturn(mockContext)


        viewModel = HomeViewModel(FakeRepository(), mockContext)
    }

    @Test
    fun `verify livedata isempty when data is empty`() {

        viewModel.getProducts()
        val data = viewModel.liveProduct.getOrAwaitValueUnitTest()
        TestCase.assertTrue(data.data == null)
    }

}