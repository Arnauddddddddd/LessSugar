package com.example.lesssugar

import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException

suspend fun getData(code: String): String? = withContext(Dispatchers.IO) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://world.openfoodfacts.net/api/v2/product/$code")
        .build()

    return@withContext try {
        println("Début de la requête...")
        val response = client.newCall(request).execute()

        response.use {
            println("Réponse reçue - Code: ${response.code}")

            if (response.isSuccessful) {
                val body = response.body?.string()
                println("Corps reçu - Taille: ${body?.length ?: 0}")
                body
            } else {
                println("Erreur HTTP: ${response.code} - ${response.message}")
                null
            }
        }
    } catch (e: IOException) {
        println("Erreur IO: ${e.message}")
        e.printStackTrace()
        null
    } catch (e: Exception) {
        println("Erreur générale: ${e.message}")
        e.printStackTrace()
        null
    }
}



