package com.priyam.galleryimages

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
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
}