package com.deromang.test.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.deromang.test.R
import com.deromang.test.databinding.SecondFragmentBinding
import com.deromang.test.model.DetailResponseModel
import com.deromang.test.model.FavoriteResult
import com.deromang.test.model.ListResponseModel
import com.deromang.test.util.setImageUrl
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding = SecondFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        setupObservables(binding)

        args.model?.let { model : ListResponseModel ->

            setupView(binding, model)

            viewModel.getDetail()

            model.propertyCode?.let {
                viewModel.isFavorite(it)
            }

        }

        return binding.root
    }

    private fun setupObservables(binding: SecondFragmentBinding) {
        viewModel.getDetailResult.observe(viewLifecycleOwner) { result ->
            result.success?.let { model ->
                updateDetail(binding, model)
            }
            result.error?.let { error ->
                Toast.makeText(requireContext(), getString(error), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isFavoriteResult.observe(viewLifecycleOwner) { result ->

            result.success?.let { favoriteModel ->
                setupDesignFavorite(binding, favoriteModel)
            }

            result.error?.let { error ->
                Toast.makeText(requireContext(), getString(error), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateDetail(binding: SecondFragmentBinding, model: DetailResponseModel) {

    }

    private fun setupView(binding: SecondFragmentBinding, model: ListResponseModel) {
        binding.tvName.text = model.district

        binding.tvType.text = model.neighborhood

        binding.tvLocation.text = model.address

        model.thumbnail?.let {
            binding.ivDetail.setImageUrl(requireContext(), it)
        }

        binding.fabFavorite.setOnClickListener {
            model.isFavorite = !model.isFavorite

            model.propertyCode?.let {
                if (model.isFavorite) {
                    viewModel.addFavorite(it)
                } else {
                    viewModel.removeFavorite(it)
                }
            }

        }
    }

    private fun setupDesignFavorite(binding: SecondFragmentBinding, model: FavoriteResult) {
        model.id?.let {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_on)
            model.dateAdded?.let { date ->
                updateDate(binding, date)
            }
        } ?: run {
            binding.fabFavorite.setImageResource(R.drawable.ic_add)
            binding.tvDateAdded.visibility = View.GONE
        }
        binding.fabFavorite.invalidate()
    }

    private fun updateDate(binding: SecondFragmentBinding, dateAdded: Long) {
        binding.tvDateAdded.visibility = View.VISIBLE
        binding.tvDateAdded.text = formatDate(dateAdded)
    }

    private fun formatDate(dateInMillis: Long): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val date = Date(dateInMillis)
        return dateFormat.format(date)
    }

}