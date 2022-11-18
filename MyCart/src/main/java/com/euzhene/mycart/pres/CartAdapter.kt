package com.euzhene.mycart.pres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.euzhene.mycart.databinding.CartItemBinding
import com.euzhene.mycart.domain.CartItem

class CartAdapter : ListAdapter<CartItem, CartAdapter.ViewHolder>(CartDiffCallback()) {
    var onItemDelete: ((CartItem) -> Unit)? = null
    var onItemCountDecrease: ((CartItem) -> Unit)? = null
    var onItemCountIncrease: ((CartItem) -> Unit)? = null

    class ViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun initItem(item: CartItem) {
            with(binding) {
                tvNameCartItem.text = item.title
                tvPriceCartItem.text = "$${item.price}"
                tvCounter.text = item.count.toString()
                ivCartItem.load(item.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.initItem(item)
        with(holder.binding) {
            btnDecrease.setOnClickListener { onItemCountDecrease?.invoke(item) }
            btnIncrease.setOnClickListener { onItemCountIncrease?.invoke(item) }
            btnDeleteCartItem.setOnClickListener { onItemDelete?.invoke(item) }
        }
    }
}