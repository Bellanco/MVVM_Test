package com.deromang.test.ui.first.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deromang.test.databinding.RowMainBinding
import com.deromang.test.model.ListResponseModel
import com.deromang.test.util.setImageUrl

class FirstViewHolder private constructor(
    private val binding: RowMainBinding,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(model: ListResponseModel, context: Context) {

        binding.tvName.text = model.district

        binding.tvState.text = model.neighborhood

        model.thumbnail?.let {
            binding.ivMain.setImageUrl(context, it)
        }

        binding.clContainer.setOnClickListener {
            listener.onClick(model)
        }

    }

    companion object {
        fun from(parent: ViewGroup, listener: OnItemClickListener): FirstViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RowMainBinding.inflate(inflater, parent, false)
            return FirstViewHolder(binding, listener)
        }
    }


    interface OnItemClickListener {
        fun onClick(model: ListResponseModel)
    }
}