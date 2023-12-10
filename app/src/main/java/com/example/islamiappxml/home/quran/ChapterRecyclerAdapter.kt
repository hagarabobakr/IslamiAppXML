package com.example.islamiappxml.home.quran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiappxml.R

class ChapterRecyclerAdapter(val items : List<String>) : RecyclerView.Adapter<ChapterRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
     val  title : TextView
     init {
         title = itemView.findViewById(R.id.title)
     }
    }
    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter,parent,false)
        return ViewHolder(view)
    }
    var onItemClickListener : OnItemClickListener? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position]
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, items[position])
            }
        }
        if(holder.adapterPosition == items.size-1){
            holder.itemView.findViewById<View>(R.id.seprator)
                .visibility = View.GONE
        }
    }


}