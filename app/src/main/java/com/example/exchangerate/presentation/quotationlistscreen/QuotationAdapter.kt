package com.example.exchangerate.presentation.quotationlistscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R
import com.example.exchangerate.presentation.quotationlistscreen.QuotationAdapter.RateHolder

class QuotationAdapter(
    private val items: List<Pair<String, String>>,
    private val listener: (currency: String) -> Unit
) : ListAdapter<Pair<String, String>, RateHolder>(QuotationComparator()){

    class RateHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCurrency: TextView = view.findViewById(R.id.tvCurrency)
        private val tvValue: TextView = view.findViewById(R.id.tvValue)

        fun bind(item: Pair<String, String>) {
            tvCurrency.text = item.first
            tvValue.text = item.second
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rate_item, parent, false)
        return RateHolder(view)
    }

    override fun onBindViewHolder(holder: RateHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            listener(items[position].first)
        }
    }

    class QuotationComparator : DiffUtil.ItemCallback<Pair<String, String>>() {
        override fun areItemsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean =
            oldItem.first == newItem.first

        override fun areContentsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean =
            oldItem == newItem
    }
}