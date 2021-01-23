package com.example.dogs.view.dogList.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dogs.networkcalls.DogApiService
import com.example.dogs.repository.DataRepo
import com.example.dogs.roomdb.DogDatabase
import com.example.dogs.util.isNetworkAvailable
import com.example.dogs.model.DogBreed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ListViewModel(application: Application): AndroidViewModel(application) {




    private val dogService = DogApiService()
    private val DogRepo = DataRepo(DogDatabase.getDatabase(getApplication()))
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val internetAval= MutableLiveData<Boolean>()
    val dataavalalbe = MutableLiveData<Int>()


    fun refresh() {
        internetAval.value=isNetworkAvailable(getApplication())
        CoroutineScope(IO).launch {
            if(internetAval.value==true){
                fetchFromRemote()
            }
            else{
                fetchFromDatabase()
            }

        }
    }

    private suspend fun fetchFromRemote() {
        Log.d("ListView","Intenet Avalable")
        storeDogsLocally(dogService.getDogs())
   //     Toast.makeText(getApplication(), "Dogs retrieved from endpoint", Toast.LENGTH_SHORT).show()

    }

    private suspend fun fetchFromDatabase() {
        loading.postValue( true)
            dataavalalbe.postValue(DogRepo.getAllDogCount())
            if(dataavalalbe.value==0){
                loading.postValue(false)
                dogsLoadError.postValue(true)
            }else {
                val dogs = DogRepo.getAllDogs()
                dogsRetrieved(dogs)
            }

//        Toast.makeText(getApplication(), "Dogs retrieved from database", Toast.LENGTH_SHORT).show()
    }



    private fun storeDogsLocally(list: List<DogBreed>) {
        CoroutineScope(IO).launch {
            DogRepo.deleteAllDogs()
            DogRepo.insetAllDogs(list)
            dogsRetrieved(DogRepo.getAllDogs())
        }
    }


    private fun dogsRetrieved(list: List<DogBreed>) {
        dogs.postValue(list)
        dogsLoadError.postValue(false)
        loading.postValue(false)
    }



}


