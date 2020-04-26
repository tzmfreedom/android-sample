package com.freedom_man.standard

import io.reactivex.Observable
import retrofit2.http.GET

interface TweetApiInterface {
    @GET("/macros/s/AKfycby0ZJd8R_krdNPwgr0BOGc58lFb_99ThhIu8qwcA36c8pWEw48/exec")
    fun getItems(): Observable<List<TweetItem>>
}