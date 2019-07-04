package dev.anhlt.tikitest.APIs

import dev.anhlt.tikitest.App
import dev.anhlt.tikitest.R
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIServices {
    inline fun <reified T : Any> getService(): T {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(App.getContext().getString(R.string.server_url))
            .build()
        return retrofit.create(T::class.java)
    }
}