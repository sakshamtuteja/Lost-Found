package com.example.lostfound.FoundRecycler

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.ImagesAdapterF
import com.example.lostfound.LostRecycler.ImagesAdapterLost
import com.example.lostfound.LostRecycler.itemadapterlost
import com.example.lostfound.R
import com.example.lostfound.databinding.ActivityImagesfoundactivityBinding
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

class Imagesfoundactivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var itemRecyclerview: RecyclerView
    private lateinit var itemArraylist:ArrayList<ImagesAdapterFound>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagesfoundactivity)
        itemRecyclerview =findViewById(R.id.item_list)
        itemRecyclerview.layoutManager = LinearLayoutManager(this)
        itemRecyclerview.hasFixedSize()
        itemArraylist = arrayListOf<ImagesAdapterFound>()
        getItemData()
    }
    private fun getItemData() {
        db = FirebaseDatabase.getInstance().getReference("itemsfound")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (itmsnapshot in snapshot.children){
                        val item = itmsnapshot.getValue(ImagesAdapterFound::class.java)
                        itemArraylist.add(item!!)
                    }
                    itemRecyclerview.adapter = itemadapterfound(itemArraylist)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}