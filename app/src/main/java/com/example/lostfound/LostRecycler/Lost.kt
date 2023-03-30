package com.example.lostfound.LostRecycler

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lostfound.databinding.FragmentLostBinding
import com.google.firebase.database.*


class Lost : Fragment() {
    private var _binding: FragmentLostBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: itemadapterlost
    private lateinit var list: ArrayList<ImagesAdapterLost>
    var databaseReference: DatabaseReference? = null
    var eventListener : ValueEventListener?= null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLostBinding.inflate(inflater, container, false)
        binding.floatingActionButton2.setOnClickListener {
            activity?.let {
                val intent = Intent(it, Uploadlost::class.java)
                it.startActivity(intent)
            }

        }
        val gridLayoutManager = GridLayoutManager(context, 1)
        binding.itemList.layoutManager = gridLayoutManager
        list = ArrayList()
        adapter = itemadapterlost(list)
        binding.itemList.adapter = adapter


        databaseReference = FirebaseDatabase.getInstance().reference
        val reference = databaseReference!!.child("itemslost")

        eventListener = reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (itemSnapshot in snapshot.children) {
                    //val dataClass = itemSnapshot.getValue(UserId::class.java)
                    val itemlost = itemSnapshot.child("itemLost").value.toString()
                    val datelost = itemSnapshot.child("datelost").value.toString()
                    val place = itemSnapshot.child("placelost").value.toString()
                    val img = itemSnapshot.child("itemImg").value.toString()
                    list.add(ImagesAdapterLost(itemlost, datelost, place, img))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


                    return binding.root
                }
            }
