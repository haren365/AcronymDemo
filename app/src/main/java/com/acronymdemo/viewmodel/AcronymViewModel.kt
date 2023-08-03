package com.acronymdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acronymdemo.model.AcronymDataModel
import com.acronymdemo.repository.AcronymsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AcronymViewModel(private val repository: AcronymsRepository): ViewModel() {

    private val _acronyms = MutableLiveData<List<AcronymDataModel>>()
    val acronyms: LiveData<List<AcronymDataModel>> get() = _acronyms

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

     val editTextWord = MutableLiveData<String>()

    fun getAcronymsOnButtonClick() {
        val word = editTextWord.value!!
        fetchAcronyms(word)
    }

    fun fetchAcronyms(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAcronyms(word)
                _acronyms.postValue(response)
            } catch (e: Exception) {
                _error.postValue("Error occurred: ${e.message}")
            }
        }
    }

}