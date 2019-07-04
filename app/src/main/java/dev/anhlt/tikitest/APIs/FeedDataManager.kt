package dev.anhlt.tikitest.APIs

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object FeedDataManager {
    fun getKeywords(success: ((List<String>) -> Unit)? = null, failed: ((Throwable) -> Unit)? = null) {
        APIServices.getService<CategoryAPIs>()
            .getKeywords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                success?.invoke(result)
            }, { error ->
                failed?.invoke(error)
            })
    }
}