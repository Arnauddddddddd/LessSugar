package com.example.lesssugar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.json.JSONObject




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val scanButton = findViewById<Button>(R.id.scan_button)
        val editText = findViewById<EditText>(R.id.textCode)
        val historyList = findViewById<ListView>(R.id.listView)



        scanButton.setOnClickListener {
            val textCode = editText.text.toString()
            loadData(textCode)
        }

    }

    private fun loadData(code: String) {
        lifecycleScope.launch {
            val data = getData(code)
            val json = JSONObject(data)
            val product = json.getJSONObject("product")
            val nutriments = product.getJSONObject("nutriments")
            val sugars100g = nutriments["sugars_100g"]
            val nutritionScoreFr = nutriments["nutrition-score-fr"]
            //println("Mes données : $data")
            println(sugars100g)
        }
    }


}