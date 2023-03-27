package com.example.lostfound.FoundRecycler

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.example.lostfound.R

class itemadapterfound (private val itmlst:ArrayList<ImagesAdapterFound>):
    RecyclerView.Adapter<itemadapterfound.itmHolder>() {
    class itmHolder(itmView: View):RecyclerView.ViewHolder(itmView){
        val itmname: EditText =itmView.findViewById(R.id.editname)
        val itmdate: EditText =itmView.findViewById(R.id.editdate)
        val itmplace: EditText =itmView.findViewById(R.id.editplace)
        val itmimg: ImageView =itmView.findViewById(R.id.itm_img)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemadapterfound.itmHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemfound,parent,
            false)
        return itemadapterfound.itmHolder(itemView)
    }
    override fun getItemCount(): Int {
        return itmlst.size
    }

    override fun onBindViewHolder(holder: itemadapterfound.itmHolder, position: Int) {
        val currentitem = itmlst[position]
        holder.itmname.setText(currentitem.itemLost.toString())
        holder.itmdate.setText(currentitem.datelost.toString())
        holder.itmplace.setText(currentitem.placelost.toString())
        val bytes = android.util.Base64.decode(currentitem.itemImg,
            android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.itmimg.setImageBitmap(bitmap)
    }
}