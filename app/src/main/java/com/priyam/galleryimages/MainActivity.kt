package com.priyam.galleryimages

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.Toast

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
            pickImagesIntent()
        }
        next.setOnClickListener {
            if (position < images!!.size - 1) {
                position++
                imageSwitcher.setImageURI(images!![position])
            } else {
                Toast.makeText(this, "No More IMages to show", Toast.LENGTH_SHORT).show()
            }
        }
        previous.setOnClickListener {
            if (position > 0) {
                position--
                imageSwitcher.setImageURI(images!![position])
            } else {
                Toast.makeText(this, "No More IMages to show", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImagesIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select image(s)"), PICK_IMAGES_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGES_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data!!.clipData != null) {
                    //picked multiple images
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        images!!.add(imageUri)

                    }
                    //first image to switcher
                    imageSwitcher.setImageURI(this!!.images!![0])
                    position = 0
                } else {
                    //picked single images
                    val imageUri = data.data
                    imageSwitcher.setImageURI(imageUri)
                    position = 0

                }
            }
        }
    }

}