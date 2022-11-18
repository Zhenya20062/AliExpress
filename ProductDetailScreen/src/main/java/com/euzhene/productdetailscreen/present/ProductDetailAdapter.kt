package com.euzhene.productdetailscreen.present

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.euzhene.productdetailscreen.databinding.ProductPictureItemBinding

class ProductDetailAdapter(
    private val list: List<String>,
) : RecyclerView.Adapter<ProductDetailAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ProductPictureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun changeImage(item: String) {
            binding.ivProductPicture.load(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductPictureItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.changeImage(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}