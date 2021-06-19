package com.example.trialkotlin3

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false,
            )
        )
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)

    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo = todos[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvTodoTitle).text = currTodo.title
            findViewById<CheckBox>(R.id.cbDone).isChecked = currTodo.isChecked
//            tvTodoTitle.text = currTodo.title
//            cbDone.isChecked = currTodo.isChecked

            toggleStrikeThrough(findViewById(R.id.tvTodoTitle), currTodo.isChecked)
            findViewById<CheckBox>(R.id.cbDone).setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(findViewById(R.id.tvTodoTitle), isChecked)
                currTodo.isChecked = !currTodo.isChecked
            }

        }
        holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
//        holder.itemView.apply {
//            //tvTodoTitle
//
//        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}