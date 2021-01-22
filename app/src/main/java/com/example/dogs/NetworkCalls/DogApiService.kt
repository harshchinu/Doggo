                                                                                                                                                                                                                                                                                                package com.example.dogs.NetworkCalls

import com.example.dogs.model.DogBreed
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DogApiService {

    private val BASEURL="https://raw.githubusercontent.com/"

    private val api =Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DogsApi::class.java)


    suspend fun getDogs():List<DogBreed>{
        return api.getDogs()
    }
}