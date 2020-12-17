package com.example.trainingapp.core


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.FieItem


class FileHolder(parent: ViewGroup, imageSize: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_customization, parent, false)) {

    val image: ImageView = itemView.findViewById(R.id.customization_item_image)
    val text: TextView = itemView.findViewById(R.id.customization_item_text)

    init {
        image.layoutParams.height = imageSize
        image.layoutParams.width = imageSize
        itemView.findView(R.id.customization_item_edit).makeGone()
    }

    fun bind(fieItem: FieItem) {
        image.setImageBitmap(BitmapFactory.decodeFile(fieItem.path))
        text.text = Uri.parse(fieItem.path).lastPathSegment
    }
}