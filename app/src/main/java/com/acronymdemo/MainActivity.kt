package com.acronymdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.acronymdemo.adapter.AcronymRecyclerViewAdapter
import com.acronymdemo.apiservice.RetrofitClient
import com.acronymdemo.databinding.ActivityMainBinding
import com.acronymdemo.repository.AcronymsRepository
import com.acronymdemo.viewmodel.AcronymViewModel
import com.acronymdemo.viewmodel.AcronymViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AcronymViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val apiService = RetrofitClient.apiService
        val repository = AcronymsRepository(apiService)
        val factory = AcronymViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AcronymViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding.acronymsRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.acronyms.observe(this) {
            binding.acronymsRecyclerView.adapter = AcronymRecyclerViewAdapter(it)
        }

        viewModel.error.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                binding.errorTextview.visibility = View.VISIBLE
                binding.errorTextview.text = errorMessage
            } else binding.errorTextview.visibility = View.GONE
        }
    }


}