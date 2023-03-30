package com.example.lostfound.FoundRecycler

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lostfound.LostRecycler.Imageslostactivity
import com.example.lostfound.databinding.ActivityUploadfoundBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Uploadfound : AppCompatActivity() {
    var sImage:String? =""
    private lateinit var db: DatabaseReference
    private lateinit var binding : ActivityUploadfoundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myCalender = Calendar.getInstance()
        val datepicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfmonth ->
            myCalender.set(Calendar.YEAR,year)
            myCalender.set(Calendar.MONTH,month)
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfmonth)
            updateLable(myCalender)
        }
        binding.datepicker.setOnClickListener {
            DatePickerDialog(this, datepicker, myCalender.get(Calendar.YEAR), myCalender.get(
                Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
    private fun updateLable(myCalendar: Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.dol.setText(sdf.format(myCalendar.time))
    }
    fun insert_data(view: View) {
        val itemLost = binding.itemlost.text.toString()
        val datelost = binding.dol.text.toString()
        val placeLost = binding.placelost.text.toString()
        db = FirebaseDatabase.getInstance().getReference("itemsfound")
        val item = ImagesAdapterFound(itemLost,datelost,placeLost,sImage)
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
                val stream = ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes = stream.toByteArray()
                sImage= Base64.encodeToString(bytes, Base64.DEFAULT)
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
        i = Intent(this, Imagesfoundactivity::class.java)
        startActivity(i)

    }
}
