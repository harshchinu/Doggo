package com.example.dogs.view.DetailDogs.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.RoomDb.DogDatabase
import com.example.dogs.model.DogBreed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

class DetailViewModel(application: Application):AndroidViewModel(application) {

    var dogDetails=MutableLiveData<DogBreed>()

    fun fetch(uuid: Int){
        CoroutineScope(IO).launch{
            val dogBreed = DogDatabase(getApplication()).DogDao().getDog(uuid)
            dogDetails?.postValue(dogBreed)
        }
    }



}