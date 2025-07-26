package com.example.lesssugar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide



class ProductAdapter(context: Context, private val products: List<ProductInfo>) :
ArrayAdapter<ProductInfo>(context, 0, products){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.product_listview, parent, false)
        val product = products[position]
        val textSugar = view.findViewById<TextView>(R.id.quantitySugar)

        view.findViewById<TextView>(R.id.productName).text = product.productName
        textSugar.text = product.sugar.toString()
        val imageViewProductImage = view.findViewById<ImageView>(R.id.productImage)
        val imageViewNutriscore = view.findViewById<ImageView>(R.id.imageNutriscore)
        val imageUrlProductImage = product.productImageLink
        when (product.nutriscore) {
            "a" -> imageViewNutriscore.setImageResource(R.drawable.a)
            "b" -> imageViewNutriscore.setImageResource(R.drawable.b)
            "c" -> imageViewNutriscore.setImageResource(R.drawable.c)
            "d" -> imageViewNutriscore.setImageResource(R.drawable.d)
            "e" -> imageViewNutriscore.setImageResource(R.drawable.e)
        }

        when (product.sugar.toInt()) {
            in -1..2 -> {
                textSugar.setTextColor(Color.GREEN)
            }
            in 2 .. 4 -> {
                textSugar.setTextColor(Color.YELLOW)
            }
            in 4 .. 6 -> {
                textSugar.setTextColor(Color.rgb(255, 128, 0))
            }
            else -> {
                textSugar.setTextColor(Color.RED)
            }
        }

        if (product.sugar <= 2) {

        } else {
            textSugar.setTextColor(Color.RED)
        }

        Glide.with(imageViewProductImage.context)
            .load(imageUrlProductImage)
            .into(imageViewProductImage)

        return view
    }
}