package com.deromang.test.ui.second.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deromang.test.databinding.RowImageBinding
import com.deromang.test.model.ImageModel
import com.deromang.test.util.setImageUrl

class SecondAdapter(private val images: List<ImageModel?>?) : RecyclerView.Adapter<SecondAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: RowImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImageModel) {
            binding.ivDetail.setImageUrl(binding.root.context, image.url ?: "")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = RowImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        images?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = images?.size ?: 0
}
