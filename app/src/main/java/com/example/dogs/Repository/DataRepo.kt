package com.example.dogs.Repository

import android.app.Application
import androidx.room.Database
import com.example.dogs.NetworkCalls.DogApiService
import com.example.dogs.RoomDb.DogDao
import com.example.dogs.RoomDb.DogDatabase

/*class DataRepo(application: Application) {
    private val dogService = DogApiService()
    private val DogDao =DogDatabase(application).DogDao()

    var instance:DataRepo?=null

    fun getInstance(application: Application):DataRepo{
        if(instance==null)
            instance=DataRepo(application)
        return instance as DataRepo
    }

    fun Doa():DogDao{
        return DogDao
    }

    fun DogInternet():DogApiService{
        return dogService
    }

}*/

object DataRepo{

    private val dogService = DogApiService()
    private var dogDao:DogDao?=null


    fun getDoa(application: Application):DogDao{
        dogDao=DogDatabase(application).DogDao()
        return dogDao as DogDao
    }

    fun getDogInternet():DogApiService{
        return dogService
    }
}