package com.android.facedetection

import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import android.graphics.Bitmap




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView=findViewById<AppCompatImageView>(R.id.image)



        //step 2 Configure the face detector
        val options=FaceDetectorOptions.Builder().
                setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL).build()





        //step 3 prepare the input image
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val mutableBitmap: Bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val image = InputImage.fromBitmap(mutableBitmap, 0)



        //step 4 Create an Instance of a Face Detector
        val detector = FaceDetection.getClient(options)






        //step 5 Send Image to the Face Detector and Process the Image
        detector.process(image)
            .addOnSuccessListener { faces ->
                // step 6 call draw canvas class
                val drawingView=DrawRect(applicationContext,faces)
                drawingView.draw(Canvas(mutableBitmap))
                runOnUiThread { imageView.setImageBitmap(mutableBitmap) }

            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Toast.makeText(baseContext, "Oops, something went wrong!",
                    Toast.LENGTH_SHORT).show()
            }

    }
}