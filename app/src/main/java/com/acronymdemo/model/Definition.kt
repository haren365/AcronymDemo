package com.acronymdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("definition") @Expose val definition: String
)
