package com.example.lostfound

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostfound.databinding.ActivityImagesfoundactivityBinding
import com.example.lostfound.databinding.ActivityImageslostactivityBinding
import com.google.firebase.firestore.FirebaseFirestore

class Imagesfoundactivity : AppCompatActivity() {

    private lateinit var binding: ActivityImagesfoundactivityBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mListi = mutableListOf<String>()
    private lateinit var adapterFound: ImagesAdapterFound

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesfoundactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVars()
        getImages()
    }
    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapterFound = ImagesAdapterFound(mListi)
        binding.recyclerView.adapter = adapterFound
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getImages(){
        firebaseFirestore.collection("imagesfound")
            .get().addOnSuccessListener {
                for (i in it) {
                    mListi.add(i.data["pic"].toString())

                }
                adapterFound.notifyDataSetChanged()
            }
    }
}