package com.example.dogs.view.dogList.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.util.getProgressDrawable
import com.example.dogs.util.loadImage
import com.example.dogs.model.DogBreed
import com.example.dogs.view.dogList.ListFragmentDirections
import kotlinx.android.synthetic.main.item_dog.view.*

class DogListAdapter(val dogsList:ArrayList<DogBreed>):RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    fun updateDogList(newDogList:List<DogBreed>){
        dogsList.clear();
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }

    class DogViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_dog,parent,false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text=dogsList[position].dogBreed
        holder.view.lifespan.text=dogsList[position].lifespan
        holder.view.setOnClickListener {
            val uuid = dogsList[position].uuid.toString().toInt()
            Log.d("Adaper",uuid.toString())
            val action = ListFragmentDirections.toDetailFragment()
            action.dogUuid = uuid
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.loadImage(dogsList[position].imageUrl,getProgressDrawable(holder.view.imageView.context))


    }

    override fun getItemCount()=dogsList.size

}