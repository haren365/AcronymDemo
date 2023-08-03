package com.acronymdemo.apiservice

import com.acronymdemo.model.AcronymDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/v2/entries/en/{word}")
    suspend fun getAcronyms(
        @Path("word") word: String
    ): List<AcronymDataModel>
}