package com.example.sdktestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        agent_btn.setOnClickListener {
            val intent = Intent(this, Agent_Sdk_Test::class.java)
            startActivity(intent)
        }

        user_btn.setOnClickListener {
            val intent = Intent(this, User_Sdk_Test::class.java)
            startActivity(intent)
        }

        user_ionic_btn.setOnClickListener {
            val intent = Intent(this, User_Ionic_Sdk_Test::class.java)
            startActivity(intent)
        }

        agentless_btn.setOnClickListener {
            val intent = Intent(this, Agentless_Sdk_Test::class.java)
            startActivity(intent)
        }

    }
}
