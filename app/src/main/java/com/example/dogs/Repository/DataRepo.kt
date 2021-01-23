package com.example.dogs.repository

import com.example.dogs.roomdb.DogDao
import com.example.dogs.roomdb.DogDatabase
import com.example.dogs.model.DogBreed

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

class DataRepo(val database: DogDatabase){

    private var dogDao:DogDao=database.DogDao()


    suspend fun getAllDogCount(): Int {
        return dogDao.getAllDogCount()
    }

    suspend fun deleteAllDogs(){
        dogDao.getAllDogs()
    }

    suspend fun insetAllDogs(list:List<DogBreed>) {
        dogDao.insertAll(*list.toTypedArray())
    }

    suspend fun getAllDogs(): List<DogBreed> {
        val result=dogDao.getAllDogs()
        return result
    }

    suspend fun getDog(uuid:Int):DogBreed = dogDao.getDog(uuid)


}