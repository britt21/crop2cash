package com.example.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.common.DataManager
import com.example.common.NetworkHelper
import com.example.common.Product
import com.example.common.model.ProductsItem
import com.example.homeactivity.databinding.ActivityHomeBinding
import com.example.network.room.SingleProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(),Onclick {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    val shimadapter = ProductAdapter(this,this)
    val imageAdapter = ImageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        window.statusBarColor = ContextCompat.getColor(this, R.color.drk_prpl)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getProducts()


        val soonintent = Intent(this,ComingSoonActivity::class.java)
        binding.msgct.setOnClickListener {
            startActivity(soonintent)
        }
        binding.searchit.doOnTextChanged { text, start, before, count ->
            Toast.makeText(this,"Search is Not Availible",Toast.LENGTH_SHORT).show()
        }

        binding.camit.setOnClickListener {
            startActivity(soonintent)
        }

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

    @OptIn(DelicateCoroutinesApi::class)
    override fun click(productsItem: SingleProduct) {
        val dataManager = DataManager(this)
                val products = Product(productsItem.image,productsItem.title)
                GlobalScope.launch {
                    dataManager.saveProduct(products)
                }
                val intent = Intent(this,DetailsActivity::class.java)
                startActivity(intent)

    }

}