package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        btn_2.setOnClickListener{
            val intent = Intent (this,NewActivity::class.java)
                //intent.putExtra("computer", switch1.isChecked)
            startActivity(intent)
        }
        btn_4.setOnClickListener{
            finish()
            exitProcess(0);
        }
    }
}
