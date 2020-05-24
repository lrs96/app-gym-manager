    package br.com.app_my_manage_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MapsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
    }

    override fun onResume() {
        super.onResume()
        val mapFragment = MapsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.layoutMaps, mapFragment).commit()
    }
}
