package com.example.dogs.RoomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogs.model.DogBreed

@Database(entities = arrayOf(DogBreed::class),version = 1)
abstract class DogDatabase: RoomDatabase() {

    abstract fun DogDao():DogDao

    companion object{
        @Volatile private var instance:DogDatabase?=null

        operator fun invoke(context: Context)= instance?: synchronized(this){
            instance?: Room.databaseBuilder(
                context.applicationContext,
                DogDatabase::class.java,
            "dogbatabase"
            ).build()
        }


    }

}