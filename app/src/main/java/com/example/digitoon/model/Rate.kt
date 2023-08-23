package com.example.digitoon.model

import com.google.gson.annotations.SerializedName

data class Rate(

    @SerializedName("Source")
    val source: String,

    @SerializedName("Value")
    val value: String

)