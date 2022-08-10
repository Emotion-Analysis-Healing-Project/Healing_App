package com.example.healing_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodayRecyclerViewAdapter : RecyclerView.Adapter<TodayRecyclerViewAdapter.CustomViewHolder>() {
    private val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feeling, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtView: TextView = view.findViewById(R.id.time_log)

        fun bind(position: Int) {
            txtView.text = "TEXT $position"
        }
    }
}