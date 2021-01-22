package com.example.dogs.view.DogList.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.NetworkCalls.DogApiService
import com.example.dogs.NetworkCalls.DogsApi
import com.example.dogs.Repository.DataRepo
import com.example.dogs.RoomDb.DogDao
import com.example.dogs.RoomDb.DogDatabase
import com.example.dogs.Util.getProgressDrawable
import com.example.dogs.Util.isNetworkAvailable
import com.example.dogs.model.DogBreed
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class ListViewModel(application: Application): AndroidViewModel(application) {



    private val dogService = DataRepo.getDogInternet()
  //  private val DogDoa:DogDao = DataRepo.getDoa(application)
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
            dataavalalbe.postValue(DogDoa.getAllDogCount())
            if(dataavalalbe.value==0){
                loading.postValue(false)
                dogsLoadError.postValue(true)
            }else {
                val dogs = DogDatabase(getApplication()).DogDao().getAllDogs()
                dogsRetrieved(dogs)
            }

//        Toast.makeText(getApplication(), "Dogs retrieved from database", Toast.LENGTH_SHORT).show()
    }



    private fun storeDogsLocally(list: List<DogBreed>) {
        CoroutineScope(IO).launch {
            DogDoa.deleteAllDogs()
            val result = DogDoa.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }
            dogsRetrieved(DogDoa.getAllDogs())
        }
    }


    private fun dogsRetrieved(list: List<DogBreed>) {
        dogs.postValue(list)
        dogsLoadError.postValue(false)
        loading.postValue(false)
    }



}


