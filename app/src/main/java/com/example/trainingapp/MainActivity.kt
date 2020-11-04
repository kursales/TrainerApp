package com.example.trainingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

//    override fun onBackPressed() {
//        val fm = supportFragmentManager
//        var backPressedListener: BackPressedListener? = null
//       for (i in fm.fragments){
//           (i as NavHostFragment).childFragmentManager.fragments.forEach {
//               if (it is BackPressedListener) {
//                   backPressedListener = it
//                   return@forEach
//               }
//
//           }
//
//       }
//        if (backPressedListener != null) {
//            backPressedListener!!.onBackPressed()
//        } else {
//            super.onBackPressed()
//        }
//    }

}