package com.example.homeactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.model.ProductsItem
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcher = MainDispatcher()

    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(FakeRepository())
    }

    @Test
    fun `verify livedata isempty when data is empty`() {
        viewModel.getProducts()
        val data = viewModel.liveProduct.getOrAwaitValueUnitTest()
        TestCase.assertTrue(data.data == null)
    }


}