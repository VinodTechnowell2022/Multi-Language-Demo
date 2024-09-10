package com.tw.languagedemo.ui


import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tw.languagedemo.R

class LanguageAdapter(list: List<ChangeLanguage.LanguageData>) : RecyclerView.Adapter<LanguageAdapter.ExampleViewHolder>() {

    private var mList: List<ChangeLanguage.LanguageData>
    var onItemClick: ((ChangeLanguage.LanguageData) -> Unit)? = null

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView

        init {
            name = itemView.findViewById(R.id.name)

            itemView.setOnClickListener {
                onItemClick?.invoke(mList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.language_row, parent, false)
        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem: ChangeLanguage.LanguageData = mList[position]
        holder.name.text = currentItem.name

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun filterList(filteredList: List<ChangeLanguage.LanguageData>) {
        mList = filteredList
        notifyDataSetChanged()
    }

    init {
        mList = list
    }

    interface OnItemClick {
        fun onItemClicked(position: Int, view: View)
    }
}