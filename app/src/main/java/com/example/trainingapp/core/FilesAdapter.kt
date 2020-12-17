package com.example.trainingapp.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.db.Entity.FieItem
import com.example.trainingapp.newexercise.FilesDialog
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class FilesAdapter(
    val dialog: FilesDialog,
    val data: List<FieItem>,
    val imageSize: Float,
    val callback: (File) -> Unit
) : RecyclerView.Adapter<FileHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        return FileHolder(parent, imageSize.toInt()).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position == RecyclerView.NO_POSITION) return@setOnClickListener
                try {
                    val file = file(itemView.context, Date().time.toString(), filesDir = true)
                    getBitmap(this, data[position])?.let { bitmap ->
                        if (save(bitmap, file, data[position].path.endsWith(".jpg", ignoreCase = true))) {
                            callback(file)
                            dialog.dismiss()
                        }
                    }
                } catch (e: Exception) {
                    Log.d("sd", data[position].toString())
                }
            }
        }
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        holder.bind(data[position])
    }

    fun getBitmap(holder: FileHolder, fieItem: FieItem): Bitmap? {
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(holder.itemView.context.contentResolver, fieItem.uri))
        } else {
            decodePath(fieItem.path)
        }
        if (bitmap != null && (bitmap.height > imageSize || bitmap.width > imageSize)) {
            return getResizedBitmap(bitmap)
        }
        return bitmap
    }

    private fun decodePath(path: String): Bitmap? {
        return try {
            BitmapFactory.decodeFile(path)
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            null
        }
    }

    override fun getItemCount() = data.size

    fun getResizedBitmap(bm: Bitmap): Bitmap? {
        val width = bm.width
        val height = bm.height

        val matrix = Matrix()
        if (width > height) {
            matrix.postScale(imageSize / width, imageSize / width)
        } else {
            matrix.postScale(imageSize / height, imageSize / height)
        }

        val resizedBitmap = Bitmap.createBitmap(
            bm, 0, 0, width, height, matrix, false
        )
        bm.recycle()
        return resizedBitmap
    }

    fun save(bitmap: Bitmap, file: File, jpg: Boolean): Boolean {
        val out = ByteArrayOutputStream()
        bitmap.compress(if (jpg) {
            Bitmap.CompressFormat.JPEG
        } else {
            Bitmap.CompressFormat.PNG
        }, 100, out)

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            fos.write(out.toByteArray())
            return true
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return false
    }

    companion object {

        fun file(context: Context, name: String, filesDir: Boolean = false): File {
            val fileFromFilesDir = File(context.filesDir, "$name.jpg")
            return if (filesDir || fileFromFilesDir.exists()) {
                fileFromFilesDir
            } else {
                File(context.cacheDir, "$name.jpg")
            }
        }
    }
}