package com.ejemplo.app.network

import com.ejemplo.app.BuildConfig
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

class CloudNetworkManager {

    // Corrección: Uso estricto de HTTPS
    private val cloudEndpoint = "https://api.servicio-nube.com/v1/users"

    fun fetchCloudData(): String {
        var response = ""
        try {
            val url = URL("$cloudEndpoint?apikey=${BuildConfig.CLOUD_API_KEY}")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            
            // Corrección: Definición de Timeouts para evitar bloqueos
            connection.connectTimeout = 5000 
            connection.readTimeout = 5000

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response += line
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
            response = "Error interno del servidor: ${e.message}"
        }
        return response
    }
}