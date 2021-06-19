package com.example.trialkotlin3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewTodo : RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var buttonTodo : Button
    private lateinit var deleteTodo : FloatingActionButton
    private lateinit var editTextTodoItem : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // initialize views
        recyclerViewTodo = findViewById<RecyclerView>(R.id.rvTodoItems)
        buttonTodo = findViewById(R.id.btnAddTodo)
        deleteTodo = findViewById(R.id.deleteTodos)
        editTextTodoItem = findViewById(R.id.etTodoTitle)


        todoAdapter = TodoAdapter(mutableListOf())
        recyclerViewTodo.adapter = todoAdapter
        recyclerViewTodo.layoutManager = LinearLayoutManager(this)

        buttonTodo.setOnClickListener {
            val todoTitle = editTextTodoItem.text.toString()
            if(!todoTitle.isEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                editTextTodoItem.text.clear()

            }
        }
        deleteTodo.setOnClickListener{
            todoAdapter.deleteDoneTodos()
        }
    }
}