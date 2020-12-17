package com.example.trainingapp.newexercise

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.core.Dialog
import com.example.trainingapp.db.Entity.FieItem
import com.example.trainingapp.core.FilesAdapter
import java.io.File
import java.util.*


class FilesDialog(context: Context, val imageSize: Float, val callback: (File) -> Unit) : Dialog(
    context
) {

    override fun getLayoutId() = R.layout.dialog_files

    private fun getAllImages(context: Context): ArrayList<FieItem> {
        val resolver = context.contentResolver
        val columns = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA)

        var arrayList = ArrayList<FieItem>()
        resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null)?.use { cursor ->
            while (cursor.moveToNext()) {
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                )
                val image = FieItem(
                    contentUri,
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                )

                for (end in arrayOf(".jpg", ".png")) {
                    if (image.path.endsWith(end, ignoreCase = true)) {
                        arrayList.add(image)
                        break
                    }
                }

            }
        }
        arrayList = arrayList.sortedWith { a, b ->
            (Uri.parse(a.path).lastPathSegment ?: "").compareTo(
                Uri.parse(b.path).lastPathSegment ?: "", true
            )
        }.toCollection(ArrayList())
        return arrayList
    }

    override fun onCreateView(view: View) {
        val images = getAllImages(view.context)
        val recyclerView: RecyclerView = view.findViewById(R.id.files_list)
        val editText = view.findViewById<EditText>(R.id.files_filter)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = FilesAdapter(this, images, imageSize, callback)

        view.findViewById<EditText>(R.id.files_filter).addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ){
            recyclerView.adapter = FilesAdapter(this@FilesDialog, images.filter {
                it.path.contains(s.toString())
            }, imageSize, callback)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}