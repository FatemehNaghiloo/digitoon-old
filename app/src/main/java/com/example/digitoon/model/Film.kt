package com.example.digitoon.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "film")
data class Film(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("imdbID")
    val imdbId: String,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Type")
    val type: String,

    @SerializedName("Poster")
    val poster: String
)