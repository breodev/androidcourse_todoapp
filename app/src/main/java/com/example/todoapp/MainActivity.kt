package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {

    private val tasks = listOf("Buy Groceries", "Call Mom", "Finish Project", "Book Flight") // Example task list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                MainScreen(tasks = tasks)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    tasks: List<String>
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("To-Do List") })
        }
    )  { innerPadding ->
        LazyColumn (
            modifier = Modifier.padding(innerPadding)
        ) {
            items(tasks) { task ->
                TaskItem(task = task, onClick = {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("TASK_ID", task)
                    }
                    context.startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun TaskItem(task: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Text(
            text = task,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

//TODO preview