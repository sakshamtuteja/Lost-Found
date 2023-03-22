package com.example.lostfound

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.databinding.EachItemLostBinding
import com.squareup.picasso.Picasso

class ImagesAdapterLost(private var mList: List<String>) :
    RecyclerView.Adapter<ImagesAdapterLost.ImagesViewHolder>() {
    inner class ImagesViewHolder(var binding: EachItemLostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = EachItemLostBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ImagesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder.binding){
            with(mList[position]){
                Picasso.get().load(this).into(imageView)

            }
        }
    }
}