package com.example.islamiappxml.model.radioRespons

import android.util.Log
import com.example.islamiappxml.Constants
import com.example.islamiappxml.model.WebServies
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        var retrofit: Retrofit?=null
         @Synchronized fun getInstance():Retrofit{
            if (retrofit == null){
                val loginInterceptor = HttpLoggingInterceptor {
                    Log.e("api", it)
                }
                loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loginInterceptor)
                    .build()
                retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
             return retrofit!!
        }
        fun getApi() : WebServies{
            return getInstance().create(WebServies::class.java)
        }
    }

}