package com.freedom_man.todo

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoAPIInterface {
    @GET("/macros/s/xxx/exec")
    fun getTodos(): Observable<List<Todo>>

    @POST("/macros/s/AKfycby0ZJd8R_krdNPwgr0BOGc58lFb_99ThhIu8qwcA36c8pWEw48/exec")
    fun createTodo(@Query("id") id: Int, @Query("title") title: String, @Query("body") body: String): Observable<Response<Unit>>
}
