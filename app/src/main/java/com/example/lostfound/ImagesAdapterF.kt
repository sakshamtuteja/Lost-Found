package com.example.lostfound


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.databinding.EachItemFoundBinding
import com.squareup.picasso.Picasso

class ImagesAdapterF(val itemLost:String?=null,
                     val datelost:String?=null,
                     val placelost:String?=null,
                     val itemImg:String?="")