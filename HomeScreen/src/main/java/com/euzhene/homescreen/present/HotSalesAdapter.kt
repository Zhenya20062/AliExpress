package com.euzhene.homescreen.present

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.euzhene.homescreen.databinding.HotSalesItemBinding
import com.euzhene.homescreen.domain.HotProduct

class HotSalesAdapter(
    private val list: List<HotProduct>
) : RecyclerView.Adapter<HotSalesAdapter.HotSalesViewHolder>() {
    var onBuyClick: ((HotProduct) -> Unit)? = null

    inner class HotSalesViewHolder(val binding: HotSalesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun changeItem(item: HotProduct) {
            with(binding) {
                ivHotSalesBack.load(item.picture) {
                    crossfade(true)
                }
                tvHotSalesNew.visibility = if (item.isNew) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                tvHotSalesTitle.text = item.title
                tvHotSalesDescription.text = item.description
                btnHotSalesBuy.visibility = if (item.isBuy) {
                    btnHotSalesBuy.setOnClickListener { onBuyClick?.invoke(item) }
                    View.VISIBLE
                } else {
                    View.GONE
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesViewHolder {
        val binding = HotSalesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HotSalesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotSalesViewHolder, position: Int) {
        holder.changeItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}