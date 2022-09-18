package com.example.homeactivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.common.model.ProductsItem
import com.example.network.room.SingleProduct

class ProductAdapter(val context : Context,val onclick: Onclick) : ListAdapter<ProductsItem, ProductAdapter.ProductViewHolder>(ProductDiffer) {
    object ProductDiffer : DiffUtil.ItemCallback<ProductsItem>(){
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.title === newItem.title
        }

        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class ProductViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val pimg = view.findViewById<ImageView>(R.id.rc_img)
        val ptitle = view.findViewById<TextView>(R.id.pname)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_listitem,parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val curList = getItem(position)
        holder.ptitle.text = curList.title
        for (images in curList.images){
            holder.pimg.load(images)
        }
        var singleProduct = SingleProduct(0,"","")

        holder.itemView.setOnClickListener {
            for (images in curList.images){
                singleProduct = SingleProduct(0,images,curList.title)
            }

            onclick.click(singleProduct)
        }
    }

}

interface Onclick{
    fun click(productsItem: SingleProduct)
}