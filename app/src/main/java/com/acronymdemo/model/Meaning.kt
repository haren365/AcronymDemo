package com.acronymdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("partOfSpeech") @Expose val partOfSpeech: String,
    @SerializedName("definitions") @Expose val definition: List<Definition>,
    @SerializedName("synonyms") @Expose val synonyms: List<String>,
    @SerializedName("antonyms") @Expose val antonyms: List<String>
)
