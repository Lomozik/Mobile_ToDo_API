package com.example.todo_api.viewmodel

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.todo_api.model.Todo
import com.example.todo_api.model.TodosApi
import kotlinx.coroutines.launch

class ToDoViewModel: ViewModel() {
    var todos = mutableStateListOf<Todo>()
        private set
    init {
        getTodosList()
    }
    private fun getTodosList() {
        viewModelScope.launch {
            var todosApi: TodosApi? = null
            try {
                todosApi = TodosApi.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e: Exception) {
                Log.d("ERROR",e.message.toString())
            }
        }
    }
}
