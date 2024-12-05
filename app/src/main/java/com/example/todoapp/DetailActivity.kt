package com.example.todoapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.theme.ToDoAppTheme

class DetailActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DetailScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel())
{
    val context = LocalContext.current
    val taskId = (context as? Activity)?.intent?.getStringExtra("TASK_ID") ?: "Unknown Task"
    LaunchedEffect(taskId) {
        taskViewModel.selectTask(taskId)
    }

    val selectedTask by taskViewModel.selectedTask.observeAsState()

    Text(
        modifier = modifier,
        text = "Details for Task: ${selectedTask ?: "Loading..."}",
        style = MaterialTheme.typography.labelLarge
    )
}


//TODO preview