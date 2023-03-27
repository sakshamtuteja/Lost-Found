package com.example.lostfound.LostRecycler


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.R
import com.google.firebase.database.*


class Imageslostactivity : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    private lateinit var itemRecyclerview: RecyclerView
    private lateinit var itemArraylist:ArrayList<ImagesAdapterLost>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageslostactivity)
        itemRecyclerview =findViewById(R.id.item_list)
        itemRecyclerview.layoutManager = LinearLayoutManager(this)
        itemRecyclerview.hasFixedSize()
        itemArraylist = arrayListOf<ImagesAdapterLost>()
        getItemData()

    }

    private fun getItemData() {
        db =FirebaseDatabase.getInstance().getReference("itemslost")
        db.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (itmsnapshot in snapshot.children){
                        val item = itmsnapshot.getValue(ImagesAdapterLost::class.java)
                        itemArraylist.add(item!!)
                    }
                    itemRecyclerview.adapter = itemadapterlost(itemArraylist)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}