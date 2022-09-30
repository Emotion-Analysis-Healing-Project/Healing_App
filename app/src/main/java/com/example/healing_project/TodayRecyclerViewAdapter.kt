package com.example.healing_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.api.Context
import kotlinx.android.synthetic.main.activity_today.view.*


class TodayRecyclerViewAdapter(val items: ArrayList<Image>, val context: Context) : RecyclerView.Adapter<TodayRecyclerViewAdapter.CustomViewHolder>() {
    private val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

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
        return items.size
    }



    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtView: TextView = view.findViewById(R.id.time_log)

        val imageView = view.imageView
        fun bind(position: Int) {
            txtView.text = "TEXT $position"
        }
    }
}