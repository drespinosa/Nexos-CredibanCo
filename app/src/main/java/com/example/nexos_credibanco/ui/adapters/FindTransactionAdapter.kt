package com.example.nexos_credibanco.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nexos_credibanco.R
import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.databinding.TableAdapterBinding
import com.example.nexos_credibanco.ui.interfaces.ICanceItem


class FindTransactionAdapter(
    val context: Context,
    private var transactions: MutableList<AuthorizationResponseVo>,
    val cancelItem: ICanceItem<AuthorizationResponseVo>
) : RecyclerView.Adapter<FindTransactionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return ViewHolder(view.inflate(R.layout.table_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = transactions[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return this.transactions.size
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TableAdapterBinding.bind(view)

        fun bind(item: AuthorizationResponseVo, position: Int) {
            setInfo(item, position)
            setListeners(item)
        }

        private fun setInfo(item: AuthorizationResponseVo, position: Int) {
            val itemNumber = (position + 1).toString()
            binding.tittleTableTextView.text = "${context.getString(R.string.transaction)} $itemNumber"
            binding.dataOneTextView.text = item.receiptId
            binding.dataTwoTextView.text = item.rrn
            binding.dataThreeTextView.text = item.statusCode
            binding.dataFourTextView.text = item.statusDescription
        }


        private fun setListeners(item: AuthorizationResponseVo) {
            binding.buttomCancelTransaction.setOnClickListener {
                cancelItem.onCancelClick(item)
                notifyDataSetChanged()
            }
        }

    }
}
