package com.priyam.galleryimages

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private var position = 0
    private val PICK_IMAGES_CODE = 0

    private var images: ArrayList<Uri?>? = null
    private lateinit var next: Button
    private lateinit var previous: Button
    private lateinit var pick: Button
    private lateinit var imageSwitcher: ImageSwitcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        next = findViewById(R.id.nextButton)
        previous = findViewById(R.id.previousButton)
        pick = findViewById(R.id.pickImage)
        imageSwitcher = findViewById(R.id.imageSwitcher)

        images = ArrayList()

        imageSwitcher.setFactory {
            ImageView(applicationContext)
        }
        pick.setOnClickListener {

        }
        next.setOnClickListener {

        }
        previous.setOnClickListener {

        }
    }

    private fun pickImagesIntent(){
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select image(s)"),PICK_IMAGES_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PICK_IMAGES_CODE){
          if(resultCode == Activity.RESULT_OK){
              if(data!!.clipData != null){
                  //picked multiple images
              }
              else{
                  //picked single images
              }
          }
        }
    }

}