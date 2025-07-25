package com.example.lesssugar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class ProductAdapter(context: Context, private val products: List<ProductInfo>) :
ArrayAdapter<ProductInfo>(context, 0, products){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.product_listview, parent, false)
        val product = products[position]

        view.findViewById<TextView>(R.id.productName).text = product.productName
        view.findViewById<TextView>(R.id.quantitySugar).text = product.sugar.toString()
//        val imageView = view.findViewById<ImageView>(R.id.productImage)
//        val imageUrl = product.productImageLink
//
//        Glide.with(this)
//            .load(imageUrl)
//            .into(imageView)

        return view
    }
}