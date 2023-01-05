package com.priyam.galleryimages

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var position = 0
    private val PICK_IMAGES_CODE = 0

    var images: ArrayList<Uri?>? = null
    private lateinit var next: Button
    private lateinit var img: ImageView
    private lateinit var pick: Button
    private lateinit var imageRecyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pick = findViewById(R.id.pickImage)
        img = findViewById(R.id.image_view)

        images = ArrayList()
        images!!.clear()


        pick.setOnClickListener {
            pickImagesIntent()
        }


    }

    private fun setUpRecyclerView(images: ArrayList<Uri?>?) {
        imageRecyclerView = findViewById(R.id.image_recycler_view)
        adapter = ImageAdapter(applicationContext, images)
        imageRecyclerView.adapter = adapter
        imageRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        Log.d(TAG, "setUpRecyclerView: $images")
        adapter.setData(this.images)

    }

    private fun pickImagesIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select image(s)"), PICK_IMAGES_CODE)

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGES_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                img.visibility = View.GONE
                if (data!!.clipData != null) {
                    //picked multiple images
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        images!!.add(imageUri)

                    }
                    //first image to switcher

                    position = 0
                } else {
                    //picked single images
                    val imageUri = data.data
                    position = 0
                    images!!.add(imageUri)

                }
            }
            else{
                Log.d("Result TAG", "onActivityResult: ")
            }
            setUpRecyclerView(images)
        }
    }

}