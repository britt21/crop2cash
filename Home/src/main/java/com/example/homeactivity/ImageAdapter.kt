package com.example.homeactivity

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

class ImageAdapter : ListAdapter<ProductsItem, ImageAdapter.ImageViewHolder>(ImageDiffer) {
    object ImageDiffer : DiffUtil.ItemCallback<ProductsItem>(){
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.title === newItem.title
        }

        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class ImageViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val pimg = view.findViewById<ImageView>(R.id.imglst)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_listitem,parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val curList = getItem(position)
        for (images in curList.images) {
            holder.pimg.load(images)
        }
    }

}