package com.rpenates.bluebank.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rpenates.bluebank.R
import com.rpenates.bluebank.models.Transaction

class TransactionItemAdapter: RecyclerView.Adapter<TransactionItemAdapter.TransactionItemViewHolder>() {

    var transactions: List<Transaction> = emptyList()
    var onItemClicked: (Transaction) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionItemViewHolder, position: Int) {
        val item = transactions[position]
        holder.render(item)
    }

    inner class TransactionItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val transactiondescription: TextView = itemView.findViewById(R.id.transaction_description)
        private val transactionamount: TextView = itemView.findViewById(R.id.transaction_amount)
        private val content = itemView.findViewById<View>(R.id.transaction_item)

        fun render(item: Transaction) {
            transactiondescription.text = item.description
            transactionamount.text = item.amount.toString()

            content.setOnClickListener {
                onItemClicked.invoke(item)
            }
        }
    }

}