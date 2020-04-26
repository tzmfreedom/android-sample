package com.freedom_man.todo

interface TodoPresenterInterface {
    fun loadTasks(
        success: (List<Todo>) -> Unit,
        failure: (Throwable) -> Unit
    )
    fun createTask(
        id: Int,
        title: String,
        body: String,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    )
}
