package com.example.healing_project

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healing_project.R
import kotlinx.android.synthetic.main.item_schedule.*
import java.util.*

class RecyclerViewAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<ViewHolderHelper>() {

    val baseCalendar = BaseCalendar()

    init {
        baseCalendar.initBaseCalendar {
            refreshView(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHelper {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolderHelper(view)
    }

    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: ViewHolderHelper, position: Int) {

        if (position % BaseCalendar.DAYS_OF_WEEK == 0) holder.tv_date.setTextColor(Color.parseColor("#bde0834e"))
        else holder.tv_date.setTextColor(Color.parseColor("#bde0834e"))

        if (position < baseCalendar.prevMonthTailOffset || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
            holder.tv_date.alpha = 0.3f
        } else {
            holder.tv_date.alpha = 1f
        }
        holder.tv_date.text = baseCalendar.data[position].toString()
    }

    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth {
            refreshView(it)
        }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        mainActivity.refreshCurrentMonth(calendar)
    }

    /*interface OnItemClickListener{
        fun onItemClick(v: View, data: TodayData, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.time_log)
        private val imgProfile: ImageView = itemView.findViewById(R.id.feeling)

        fun bind(item: TodayData) {
            txtName.text = item.time
            Glide.with(itemView).load(item.image).into(imgProfile)

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,item,pos)
                }
            }


        }
    }*/
}