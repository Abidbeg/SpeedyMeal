package com.am.speedymeal.api

import com.am.speedymeal.model.BookListItem
import com.am.speedymeal.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {

    @GET("books")
    suspend fun getBookList(
        @Query("mime_type") mime_type: String?,
        @Query("page") page: Int,
        @Query("topic") topic: String?,
        @Query("search") search: String?
    ): BookListItem
}