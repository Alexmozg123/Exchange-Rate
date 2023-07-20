package com.example.exchangerate.ui.quotationlistscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R
import com.example.exchangerate.domain.model.Quotation
import com.example.exchangerate.ui.quotationlistscreen.QuotationAdapter.RateHolder

class QuotationAdapter(
    private val listener: (Quotation) -> Unit
) : ListAdapter<Quotation, RateHolder>(QuotationComparator()){

    class RateHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCurrency: TextView = view.findViewById(R.id.tvCurrency)
        private val tvValue: TextView = view.findViewById(R.id.tvValue)

        fun bind(item: Quotation) {
            tvCurrency.text = item.nameCurrency
            tvValue.text = item.rate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rate_item, parent, false)
        return RateHolder(view)
    }

    override fun onBindViewHolder(holder: RateHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener(getItem(position))
        }
    }

    class QuotationComparator : DiffUtil.ItemCallback<Quotation>() {
        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean =
            oldItem.nameCurrency == newItem.nameCurrency

        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean =
            oldItem == newItem
    }
}