package com.dutradev.appnetflix.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutradev.appnetflix.R
import com.dutradev.appnetflix.model.Category

// Lista vertical
class CategoryAdapter(
    private val categories: List<Category>,
    val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }


    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
            val rvCategory: RecyclerView = itemView.findViewById(R.id.rv_category)
            txtTitle.text = category.name
            rvCategory.layoutManager =
                LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            rvCategory.adapter = MovieAdapter(category.movies, R.layout.movie_item, onItemClickListener)
        }

    }

}