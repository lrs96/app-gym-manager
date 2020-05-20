package br.com.app_my_manage_mobile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_foto_aluno.*
import java.util.jar.Manifest

class FotoAlunoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto_aluno);
        // var buttonCamera =  btnCamera.findViewById<Button>(R.id.btnCamera);
        var imageView =  imageView.findViewById<ImageView>(R.id.imageView);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }

        btnCamera.setOnClickListener{ abrirCamera()}
    }

    fun abrirCamera() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 || requestCode == Activity.RESULT_OK ) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
