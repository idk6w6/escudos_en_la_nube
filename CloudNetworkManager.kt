package com.ejemplo.app.network

import com.ejemplo.app.BuildConfig
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

class CloudNetworkManager {

    private val cloudEndpoint = "https://api.servicio-nube.com/v1/users"

    fun fetchCloudData(): String {
        var response = ""
        try {
            val url = URL("$cloudEndpoint?apikey=${BuildConfig.CLOUD_API_KEY}")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5000 
            connection.readTimeout = 5000

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response += line
            }
            reader.close()
        } catch (e: Exception) {
            // Corrección: Logging interno  y mensaje  para el usuario
            Log.e("NetworkSecurity", "Excepción de red al obtener datos", e)
            response = "No se pudo completar la solicitud. Por favor, intente nuevamente."
        }
        return response
    }
}