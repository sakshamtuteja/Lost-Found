package com.example.lostfound.LostRecycler

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lostfound.databinding.ActivityUploadlostBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class Uploadlost : AppCompatActivity() {
    var sImage:String? =""
    private lateinit var db: DatabaseReference
    private lateinit var binding : ActivityUploadlostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadlostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun insert_data(view: View) {
        val itemLost = binding.itemlost.text.toString()
        val datelost = binding.dol.text.toString()
        val placeLost = binding.placelost.text.toString()
        db = FirebaseDatabase.getInstance().getReference("itemslost")
        val item = ImagesAdapterLost(itemLost,datelost,placeLost,sImage)
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key
        db.child(id.toString()).setValue(item).addOnSuccessListener {
            binding.itemlost.text.clear()
            binding.dol.text.clear()
            sImage = ""
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Data not Inserted",Toast.LENGTH_SHORT).show()
        }
    }
    fun insert_Img(view: View) {
        var myfileintent = Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher.launch(myfileintent)

    }
    private val ActivityResultLauncher = registerForActivityResult< Intent,androidx.activity.result.ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:androidx.activity.result.ActivityResult ->
        if (result.resultCode== RESULT_OK){
            val uri = result.data!!.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val myBitmap = BitmapFactory.decodeStream(inputStream)
                val stream =ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes = stream.toByteArray()
                sImage= Base64.encodeToString(bytes,Base64.DEFAULT)
                binding.imagelost.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(this, "Image Selected",Toast.LENGTH_SHORT).show()
            } catch (ex: Exception){
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun showList(view: View) {
        var i: Intent
        i = Intent(this, Imageslostactivity::class.java)
        startActivity(i)

    }
}