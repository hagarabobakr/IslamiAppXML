package com.example.islamiappxml.suraDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiappxml.R

class VersesRecyclerAdapter(val items: List<String>) :
    RecyclerView.Adapter<VersesRecyclerAdapter.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_verse, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.title.setText(items[position])
        holder.content.text = "${items[position]} {${position + 1}} "
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content: TextView

        init {
            content = itemView.findViewById(R.id.content)
        }
    }
}