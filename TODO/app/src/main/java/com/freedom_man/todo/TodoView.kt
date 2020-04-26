package com.freedom_man.todo

interface TodoView {
    fun setTodoItems(items: List<Todo>)
    fun setFailure(error: Throwable)
    fun editSuccess()
}
