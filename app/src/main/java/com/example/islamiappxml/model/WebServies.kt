package com.example.islamiappxml.model

import com.example.islamiappxml.model.radioRespons.RadioResponse
import retrofit2.Call
import retrofit2.http.GET


interface WebServies {
    @GET("radios")
    fun getRadioChanels():Call<RadioResponse>

}