package com.example.homeactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter : ListAdapter<Comment, CommentAdapter.CommentViewHolder>(Differ){
    object Differ : DiffUtil.ItemCallback<Comment>(){
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.comment === newItem.comment
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.comment == newItem.comment
        }

    }

    class CommentViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.cname)
        val comment = view.findViewById<TextView>(R.id.ccomment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recieved_msg,parent,false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val curList = getItem(position)
        holder.name.text = curList.name
        holder.comment.text = curList.comment
    }
}


data class Comment (
    val name : String,
    val comment : String)