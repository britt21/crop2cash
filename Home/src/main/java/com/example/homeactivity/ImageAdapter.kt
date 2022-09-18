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

class ImageAdapter (val onclick: imageClick): ListAdapter<String, ImageAdapter.ImageViewHolder>(ImageDiffer) {
    object ImageDiffer : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.length == newItem.length
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.length === newItem.length
        }
    }

    class ImageViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val pimg = view.findViewById<ImageView>(R.id.imglst)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.img_list,parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val curList = getItem(position)
        holder.pimg.load(curList)
        holder.pimg ?: holder.pimg.load("https://www.apple.com/newsroom/images/product/iphone/standard/apple_iphone-12-spring21_purple_04202021_big.jpg.large.jpg")

        holder.itemView.setOnClickListener {
            onclick.nimahe(curList)
        }
    }



}

interface imageClick{
    fun nimahe(image : String, title : String? = "")
}