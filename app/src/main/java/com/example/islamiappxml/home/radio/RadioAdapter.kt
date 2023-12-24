package com.example.islamiappxml.home.radio


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamiappxml.databinding.ItemRadioBinding
import com.example.islamiappxml.model.radioRespons.RadiosItem


class RadioAdapter() : RecyclerView.Adapter<RadioAdapter.ViewHolder>() {
    var items = listOf<RadiosItem?>()
    var onPlayClickListner : OnItemRadioClick? = null
    var onStopClickListner : OnItemRadioClick? = null
    class ViewHolder(val viewBinding : ItemRadioBinding):RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemRadioBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.channelName.text = items[position]?.name
        if(onStopClickListner != null){
            onStopClickListner?.onItemClicked(position,items[position]!!)
        }
        if(onPlayClickListner != null){
            onPlayClickListner?.onItemClicked(position,items[position]!!)
        }
    }
    fun changeData(data : List<RadiosItem?>){
        this.items = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

}