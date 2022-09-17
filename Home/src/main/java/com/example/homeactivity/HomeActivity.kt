package com.example.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.common.NetworkHelper
import com.example.homeactivity.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    val shimadapter = ProductAdapter()
    val imageAdapter = ImageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getProducts()

        val discount = View.inflate(this, R.layout.discount_dialog, null)
        val builder = AlertDialog.Builder(this)
            .setView(discount)
            .create()
        val btn = discount.findViewById<FrameLayout>(R.id.orderbtn)
        btn.setOnClickListener {
            builder.dismiss()
        }
        builder.show()

        discount.animate().apply {
            alpha(1f)
        }

        showShimmer()

        roomProduct()

        binding.rvShimmer.apply {
            adapter = shimadapter
        }

        setContentView(binding.root)

    }

    fun showShimmer() {
        binding.rvShimmer.apply {
            animate().apply {
                alpha(1f).duration = 3000
            }
            showShimmerAdapter()
        }
    }

    fun hideShimmer() {
        binding.rvShimmer.apply {
            hideShimmerAdapter()
        }
    }

    private fun roomProduct() {
        viewModel.readProductRoom.observe(this, Observer { prodcts ->
            if (prodcts.isNotEmpty()) {
                    shimadapter.submitList(prodcts[0].products)
                    hideShimmer()

            } else {
                getnetProduct()
            }
        })
    }

    private fun getnetProduct() {
        viewModel.liveProduct.observe(this, Observer { prods ->
            Log.d("ONPRD", "${prods.data}")
            Log.d("ONPRDNSG", "${prods.message}")
if (prods != null){
            when (prods) {
                is NetworkHelper.Loading -> {
                    showShimmer()
                }
                is NetworkHelper.Success -> {
                    hideShimmer()
                    shimadapter.submitList(prods.data)
                }
                is NetworkHelper.Error -> {
                    hideShimmer()
                }

            }
    }else{
        Toast.makeText(this,"An Error Occured",Toast.LENGTH_SHORT).show()
    }
        })
    }

}