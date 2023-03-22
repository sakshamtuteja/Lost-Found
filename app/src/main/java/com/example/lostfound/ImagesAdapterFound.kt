package com.example.lostfound


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.databinding.EachItemFoundBinding
import com.squareup.picasso.Picasso

class ImagesAdapterFound(private var mListi: List<String>) :
    RecyclerView.Adapter<ImagesAdapterFound.ImagesViewHolder>() {
    inner class ImagesViewHolder(var binding: EachItemFoundBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = EachItemFoundBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ImagesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mListi.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder.binding) {
            with(mListi[position]) {
                Picasso.get().load(this).into(imageView)

            }
        }
    }
}