package com.acronymdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acronymdemo.repository.AcronymsRepository

class AcronymViewModelFactory(private val acronymsRepository: AcronymsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AcronymViewModel::class.java)) {
            return AcronymViewModel(acronymsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}