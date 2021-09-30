package com.example.chatappfirebase.FirebaseNotfaction


import com.example.chatappfirebase.FirebaseNotfaction.Constant.Constant
import com.example.chatappfirebase.FirebaseNotfaction.Interface.NotificationApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(NotificationApi::class.java)
        }
    }
}