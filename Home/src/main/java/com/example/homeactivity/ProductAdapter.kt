package com.example.homeactivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.common.DataManager
import com.example.common.Product
import com.example.common.model.ProductsItem
import com.example.network.room.SingleProduct
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductAdapter(val context : Context,val onclick: Onclick) : ListAdapter<ProductsItem, ProductAdapter.ProductViewHolder>(ProductDiffer),imageClick {
    object ProductDiffer : DiffUtil.ItemCallback<ProductsItem>(){
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.title === newItem.title
        }

        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class ProductViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val rvimg = view.findViewById<RecyclerView>(R.id.rv_img)
        val ptitle = view.findViewById<TextView>(R.id.pname)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_listitem,parent,false))
    }

    var title = ""
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val curList = getItem(position)
        holder.ptitle.text = curList.title
        setUPrecyclerview(holder.rvimg,curList.images)
        var singleProduct = SingleProduct(0,"","")

        val rv = holder.rvimg as View
        rv.setOnClickListener {
            title = curList.title
            for (images in curList.images){
                singleProduct = SingleProduct(0,images,curList.title)
            }
            onclick.click(singleProduct)
        }



    }

    fun setUPrecyclerview(recyclerView: RecyclerView,productsItem: List<String>){
        val imageAdapter = ImageAdapter(this)
        recyclerView.adapter = imageAdapter
        imageAdapter.submitList(productsItem)
    }

    override fun nimahe(image: String,title: String?) {
//        val dataManager = DataManager(context)
//        val products = Product(image,title)
//        GlobalScope.launch {
//            dataManager.saveProduct(products)
//        }
//        val intent = Intent(context,DetailsActivity::class.java)
//        context.startActivity(intent)

    }



}

interface Onclick{
    fun click(productsItem: SingleProduct)
}