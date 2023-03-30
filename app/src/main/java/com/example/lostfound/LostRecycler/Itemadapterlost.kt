package com.example.lostfound.LostRecycler

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.LostRecycler.ImagesAdapterLost
import com.example.lostfound.R

class itemadapterlost (private val itmlst:ArrayList<ImagesAdapterLost>):RecyclerView.Adapter<itemadapterlost.itmHolder>() {
    class itmHolder(itmView:View):RecyclerView.ViewHolder(itmView){
        val itmname:TextView =itmView.findViewById(R.id.editname)
        val itmdate:TextView =itmView.findViewById(R.id.editdate)
        val itmplace:TextView =itmView.findViewById(R.id.editplace)
        val itmimg:ImageView =itmView.findViewById(R.id.itm_img)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itmHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemlost,parent,
        false)
        return itmHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itmlst.size
    }

    override fun onBindViewHolder(holder: itmHolder, position: Int) {
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