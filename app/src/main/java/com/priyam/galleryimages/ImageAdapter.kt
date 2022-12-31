package com.priyam.galleryimages

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(
    private val context: Context,
    private val images: ArrayList<Uri?>?
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var items: ArrayList<Uri?>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(images?.get(position)) {
            holder.imageView.setImageURI(this)
        }

    }

    override fun getItemCount(): Int {
        return images!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)


    }

    fun setData(data: ArrayList<Uri?>?) {
        this.items = data
        notifyDataSetChanged()
    }

}