package com.example.dogs.NetworkCalls

import com.example.dogs.model.DogBreed
import io.reactivex.Single
import retrofit2.http.GET

interface DogsApi {

    @GET("DevTides/DogsApi/master/dogs.json")
    suspend fun getDogs():List<DogBreed>
}