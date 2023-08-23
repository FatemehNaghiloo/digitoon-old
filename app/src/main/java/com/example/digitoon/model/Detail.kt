package com.example.digitoon.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail")
data class Detail(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("imdbID")
    val imdbId: String,
    
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Rated")
    val rated: String,

    @SerializedName("Released")
    val released: String, 

    @SerializedName("Runtime")
    val runtime: String,

    @SerializedName("Genre")
    val genre: String,

    @SerializedName("Director")
    val director: String,

    @SerializedName("Writer")
    val writer: String,

    @SerializedName("Actors")
    val actors: String,

    @SerializedName("Plot")
    val plot: String,

    @SerializedName("Language")
    val language: String,

    @SerializedName("Country")
    val country: String,

    @SerializedName("Awards")
    val awards: String, 

    @SerializedName("Poster")
    val poster: String, 

    @SerializedName("Ratings")
    val ratings: List<Rate>,

    @SerializedName("Metascore")
    val metascore: String,

    val imdbRating: String,

    val imdbVotes: String, 

    @SerializedName("Type")
    val type: String,

    @SerializedName("Response")
    val response: String
)