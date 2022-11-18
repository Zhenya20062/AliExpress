package com.euzhene.homescreen.present

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.euzhene.homescreen.R
import com.euzhene.homescreen.databinding.FiltersBinding
import com.euzhene.homescreen.domain.FilterProduct
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FiltersBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FiltersBinding
    lateinit var filters: List<FilterProduct>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogStyle);
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFiltersClose.setOnClickListener {
            dismiss()
        }
        binding.btnFiltersDone.setOnClickListener {
            dismiss()
        }
        initFilters()
    }

    private fun initFilters() {
        with(binding) {
            filters.forEach {
                val textView = TextView(requireContext()).apply {
                    text = it.title
                    setTypeface(
                        ResourcesCompat.getFont(
                            requireContext(),
                            com.euzhene.common.R.font.mark_pro
                        ), Typeface.BOLD
                    )
                    setTextColor(resources.getColor(com.euzhene.common.R.color.dark_blue, null))
                    textSize = 20f
                }
                llFilters.addView(textView)

                val spinner = Spinner(requireContext()).apply {
                    adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.spinner_item,
                        it.options
                    )

                }
                llFilters.addView(spinner)
            }
        }
    }
}