package com.example.dogs.view.detaildogs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dogs.R
import com.example.dogs.util.getProgressDrawable
import com.example.dogs.util.loadImage
import com.example.dogs.model.DogBreed
import com.example.dogs.view.detaildogs.ViewModel.DetailViewModel

import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private var dogUuid=0
    private lateinit var viewmodel: DetailViewModel
    private var dogBreed:DogBreed?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel=ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            dogUuid= DetailFragmentArgs.fromBundle(it).dogUuid

        }
        viewmodel.fetch(dogUuid)
        observeViewModel()

        
    }

    private fun observeViewModel() {

        viewmodel.dogDetails.observe(viewLifecycleOwner, Observer { dogDetail ->
          dogDetail?.let {
              dogName.text=dogDetail.dogBreed
              doglifespan.text=dogDetail.lifespan
              dogPurpose.text=dogDetail.bredfor
              dogTemperament.text=dogDetail.temperament
              Log.d("DetailF",dogDetail.imageUrl.toString())
              context?.let {
                  dogImage?.loadImage(dogDetail?.imageUrl, getProgressDrawable(it))
              }
          }

        })
    }


}