package dev.anhlt.tikitest.APIs

import io.reactivex.Observable
import retrofit2.http.GET

interface CategoryAPIs {
    @GET("keywords.json")
    fun getKeywords(): Observable<List<String>>
}