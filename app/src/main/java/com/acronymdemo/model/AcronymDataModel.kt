package com.acronymdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AcronymDataModel(
    @SerializedName("word") @Expose val word: String,
    @SerializedName("meanings") @Expose val meanings: List<Meaning>
)
