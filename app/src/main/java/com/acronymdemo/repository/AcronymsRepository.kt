package com.acronymdemo.repository

import com.acronymdemo.apiservice.ApiService
import com.acronymdemo.model.AcronymDataModel

class AcronymsRepository(private val service: ApiService) {
    suspend fun getAcronyms(word: String): List<AcronymDataModel> {
        return service.getAcronyms(word)
    }
}