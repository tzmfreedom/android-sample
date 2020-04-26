package com.freedom_man.todo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TodoPresenter: TodoPresenterInterface {
    companion object {
        const val BASE_URI = "https://script.google.com/"
    }

    override fun loadTasks(
        success: (List<Todo>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val api = retrofit.create(TodoAPIInterface::class.java)
        api.getTodos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success(it)
            }, {
                failure(it)
            })
    }

    override fun createTask(
        id: Int,
        title: String,
        body: String,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val api = retrofit.create(TodoAPIInterface::class.java)
        api.createTodo(id, title, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success()
            }, {
                failure(it)
            })
    }
}
