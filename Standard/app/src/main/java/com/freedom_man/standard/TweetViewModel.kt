package com.freedom_man.standard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class TweetViewModel : ViewModel() {
    companion object {
        const val BASE_URI = "https://script.google.com/"
    }

    val items: MutableLiveData<List<TweetItem>> = MutableLiveData()

    fun load(failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val api = retrofit.create(TweetApiInterface::class.java)
        api.getItems()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.postValue(it)
            }, {
                failure(it)
            })
    }
}
