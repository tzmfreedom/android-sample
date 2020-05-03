package com.freedom_man.standard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TweetViewModel : ViewModel() {
    companion object {
        const val BASE_URI = "https://script.google.com/"
        const val EXPIRE_TIME = 5
    }

    val items: MutableLiveData<List<TweetItem>> = MutableLiveData()
    val detailIndex: MutableLiveData<Int> = MutableLiveData()
    val item: TweetItem?
        get() {
            return items.value?.get(detailIndex.value!!)
        }
    var expired: Calendar = Calendar.getInstance()

    fun load(success: (List<TweetItem>) -> Unit, failure: (Throwable) -> Unit) {
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
                expired = Calendar.getInstance()
                expired.add(Calendar.SECOND, EXPIRE_TIME)
                items.postValue(it)
                success(it)
            }, failure)
    }

    fun setDetailIndex(index: Int) {
        detailIndex.postValue(index)
    }

    fun isExpired(): Boolean {
        val now = Calendar.getInstance()
        return this.expired < now
    }
}
