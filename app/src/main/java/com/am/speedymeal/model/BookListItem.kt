package com.am.speedymeal.model


import com.google.gson.annotations.SerializedName

data class BookListItem(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)

data class Result(
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("bookshelves")
    val bookshelves: List<String>,
    @SerializedName("download_count")
    val downloadCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("subjects")
    val subjects: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("formats")
    val formats: Formats,
)

data class Formats(
    @SerializedName("image/jpeg")
    val imagePath: String
)

data class Author(
    @SerializedName("birth_year")
    val birthYear: Int,
    @SerializedName("death_year")
    val deathYear: Int,
    @SerializedName("name")
    val name: String
)
