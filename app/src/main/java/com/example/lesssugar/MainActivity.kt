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
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var productList: MutableList<ProductInfo>
    private lateinit var adapter: ProductAdapter

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

        productList = mutableListOf(
            ProductInfo("Coca Cola Cherry", "https://images.openfoodfacts.net/images/products/544/900/018/5945/front_fr.18.400.jpg", 10.3),
        )

        adapter = ProductAdapter(this, productList)
        historyList.adapter = adapter



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
            val productName = product.optString("product_name", "Nom inconnu")
            val productImage = product.optString("image_front_url", "Nom inconnu")
            val sugars100g = nutriments.optDouble("sugars_100g", -1.0)
            val nutritionScoreFr = nutriments.optDouble("nutrition-score-fr", -1.0)

            //println("Mes données : $data")

            val newProduct = ProductInfo(productName, productImage, sugars100g)
            productList.add(0, newProduct)
            adapter.notifyDataSetChanged()

            println(sugars100g)
        }
    }


}