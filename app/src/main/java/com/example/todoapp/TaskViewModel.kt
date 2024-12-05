package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class TaskViewModel : ViewModel() {
    private val _selectedTask = MutableLiveData<String>()
    val selectedTask: LiveData<String> get() = _selectedTask

    fun selectTask(task: String) {
        _selectedTask.value = task
    }
}