package com.light.geolocation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.light.geolocation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var vb:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
         initFragment()
    }

    private fun initFragment() {
        vb?.fragmentContainer?.id?.let {
            supportFragmentManager.beginTransaction()
                .replace(it, MainFragment.newInstance())
                .commit()
        }

    }


}