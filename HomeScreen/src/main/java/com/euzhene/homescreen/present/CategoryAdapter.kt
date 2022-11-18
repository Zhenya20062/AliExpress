package com.euzhene.homescreen.present

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.euzhene.common.R
import com.euzhene.homescreen.databinding.CategoryItemBinding
import com.euzhene.homescreen.domain.ProductCategory

class CategoryAdapter(
    private val categories: Array<ProductCategory>,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var onCategoryClick: ((ProductCategory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (categories[position].active) {
            holder.makeActive()
        } else {
            holder.makeInactive()
        }
        with(holder.binding) {
            tvCategory.text = categories[position].name
            btnCategory.setImageResource(categories[position].iconId)
            btnCategory.setOnClickListener {
                for (i in categories.indices) {
                    categories[i] = categories[i].copy(active = false)
                }
                notifyDataSetChanged()
                holder.makeActive()
                categories[position] = categories[position].copy(active = true)
                onCategoryClick?.invoke(categories[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun makeActive() {
            with(binding) {
                btnCategory.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.white))
                btnCategory.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.orange))
                tvCategory.setTextColor(ContextCompat.getColor(root.context,R.color.orange))
            }


        }

        fun makeInactive() {
            with(binding) {
                btnCategory.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.gray))
                btnCategory.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.white))
                tvCategory.setTextColor(ContextCompat.getColor(root.context,R.color.dark_blue))
            }

        }

    }

    class SpacingItemDecorator
        (
        private val verticalSpace:Int=0,
        private val horizontalSpace: Int=0
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = horizontalSpace
            outRect.bottom = verticalSpace
        }
    }
}