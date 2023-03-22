package com.example.lostfound


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostfound.databinding.ActivityImageslostactivityBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class Imageslostactivity : AppCompatActivity() {


    private lateinit var binding:ActivityImageslostactivityBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapterLost: ImagesAdapterLost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageslostactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVars()
        getImages()
    }
    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapterLost = ImagesAdapterLost(mList)
        binding.recyclerView.adapter = adapterLost
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getImages(){
        firebaseFirestore.collection("images")
            .get().addOnSuccessListener {
                for (i in it) {
                    mList.add(i.data["pic"].toString())

                }
                adapterLost.notifyDataSetChanged()
            }
    }
}