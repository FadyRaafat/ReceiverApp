package com.tocaan.receiver.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cic.su.AppDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import com.tocaan.receiver.api.models.User
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.InetSocketAddress
import java.util.*
import java.util.concurrent.Executors

class MainViewModel (application: Application) :
    AndroidViewModel(application) {
    val userDao = AppDatabase.getDatabase(application)?.userDao()
     var mHttpServer: HttpServer? = null

    fun insertUser(user: User){
        userDao?.insert(user)
    }


    fun getAllUsers(): LiveData<List<User>> {
        return userDao!!.getAllUsers()
    }

     fun streamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }

     fun sendResponse(httpExchange: HttpExchange, responseText: String) {
        httpExchange.sendResponseHeaders(200, responseText.length.toLong())
        val os = httpExchange.responseBody
        os.write(responseText.toByteArray())
        os.close()
    }



     fun startServer() {
        try {
            mHttpServer = HttpServer.create(InetSocketAddress(5000), 0)
            mHttpServer!!.executor = Executors.newCachedThreadPool()
            mHttpServer!!.createContext("/", rootHandler)
            mHttpServer!!.createContext("/test", rootHandler)
            mHttpServer!!.createContext("/saveUser", messageHandler)
            mHttpServer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    // Handler for root endpoint
     val rootHandler = HttpHandler { exchange ->
        run {
            when (exchange!!.requestMethod) {
                "GET" -> {
                    sendResponse(exchange, "Welcome to my server")
                }

            }
        }

    }

     val messageHandler = HttpHandler { httpExchange ->
        run {
            when (httpExchange!!.requestMethod) {
                "GET" -> {
                    sendResponse(httpExchange, "GET route is not available for this url")
                }
                "POST" -> {

                    val inputStream = httpExchange.requestBody
                    val requestBody = streamToString(inputStream)
                    val jsonBody = JSONObject(requestBody)
                    val gson = Gson()
                    val type = object : TypeToken<User>() {}.type
                    val user: User = gson.fromJson(jsonBody.toString(), type)
                    insertUser(user)

                    //for testing
                    sendResponse(httpExchange, "OK")

                }

            }
        }
    }

}