package com.euzhene.homescreen.present

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.euzhene.homescreen.R
import com.euzhene.homescreen.databinding.BestSellerItemBinding
import com.euzhene.homescreen.domain.BestSellerProduct

class BestSellerAdapter(
    private val list: List<BestSellerProduct>,
) : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {
    var onFavoriteClicked: ((BestSellerProduct) -> Unit)? = null
    var onItemClicked: ((BestSellerProduct) -> Unit)? = null

    inner class BestSellerViewHolder(private val binding: BestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun changeItem(item: BestSellerProduct) {
            with(binding) {
                ivBestSellerPicture.load(item.picture)

                btnBestSellerFavorite.setImageResource(
                    if (item.isFavorite) {
                        com.euzhene.common.R.drawable.ic_filled_heart
                    } else {
                        com.euzhene.common.R.drawable.ic_outlined_heart
                    }
                )
                btnBestSellerFavorite.setOnClickListener { onFavoriteClicked?.invoke(item) }
                root.setOnClickListener {
                    onItemClicked?.invoke(item)
                }
                tvBestSellerPriceWithDiscount.text = "$${item.discountPrice}"
                tvBestSellerPriceWithoutDiscount.text = "$${item.priceWithoutDiscount}"
                tvBestSellerPriceWithoutDiscount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvBestSellerName.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val binding = BestSellerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BestSellerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        holder.changeItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}