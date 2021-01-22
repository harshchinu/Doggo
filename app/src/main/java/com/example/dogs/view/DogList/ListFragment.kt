package com.example.dogs.view.DogList

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogs.R
import com.example.dogs.view.DogList.Adapters.DogListAdapter
import com.example.dogs.view.DogList.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewmodel: ListViewModel
    private val dogsListAdapter = DogListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewmodel.refresh()

        dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        refreshlayout.setOnRefreshListener {
            dogsList.visibility = View.INVISIBLE
            listError.visibility = View.GONE
            LoadingView.visibility = View.VISIBLE
            viewmodel.refresh()
            refreshlayout.isRefreshing = false
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewmodel.dogs.observe(viewLifecycleOwner, Observer { dogs ->
            dogs?.let {
                dogsList.visibility = View.VISIBLE
                dogsListAdapter.updateDogList(dogs)
            }

        })

        viewmodel.dogsLoadError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                listError.visibility = if (error) View.VISIBLE else View.GONE
            }
        })

        viewmodel.loading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                LoadingView.visibility = if (loading) View.VISIBLE else View.GONE
                if (loading) {
                    listError.visibility = View.GONE
                    dogsList.visibility = View.GONE
                }
            }
        })
    }


}





