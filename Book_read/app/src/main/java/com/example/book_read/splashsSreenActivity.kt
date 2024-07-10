package com.example.book_read

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView

class splashsSreenActivity : AppCompatActivity() {
    private lateinit var img:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashs_sreen)

        img=findViewById(R.id.firstimg)
        img.alpha=0f

        //here we do animation when the img appears with a duration

        img.animate().setDuration(4000).alpha(1f).withEndAction{
            // we then tell the function to take us the login screen
                val intent=Intent(this,login::class.java)
                startActivity(intent)

            //this is the time of animation we want
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

            //after that the activity of the splash screen ends
                finish()
            }
    }
}