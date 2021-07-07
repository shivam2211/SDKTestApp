package com.example.sdktestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_agent_sdk_test.*
import kotlinx.android.synthetic.main.activity_agent_sdk_test.btn_start_agnet
import kotlinx.android.synthetic.main.activity_agent_sdk_test.domain
import kotlinx.android.synthetic.main.activity_agentless_sdk_test.*
import org.json.JSONObject
import com.getkwikid.

class Agentless_Sdk_Test : AppCompatActivity() {

    private var data = JSONObject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agentless_sdk_test)


        btn_start_agnet.setOnClickListener {

            /* Input params for the sdk */

            data.put("client_id", client_id.text.toString())
            data.put("user_id", user_id.text.toString())

            /* Initializing the kwik-sdk */
            IpvHelper.getInstance().startIpv(this, data.toString())
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("DATA", "parent requestCode = $requestCode")
        Log.d("DATA", "parent resultCode = $resultCode")

        if(requestCode == 55) {
            var data_string: String = data?.getStringExtra("data").toString()
            Log.d("DATA", "main requestCode = $data_string")
    //            gotoResult(data_string)
        }
    }
}
