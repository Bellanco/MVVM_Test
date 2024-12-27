package com.deromang.test.ui.second

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.deromang.test.databinding.SecondFragmentBinding
import com.deromang.test.databinding.TabDotsBinding
import com.deromang.test.model.DetailResponseModel
import com.deromang.test.model.FavoriteResult
import com.deromang.test.model.ListResponseModel
import com.deromang.test.ui.second.adapter.SecondAdapter
import com.google.android.material.tabs.TabLayoutMediator
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
                viewModel.isFavorite(it, model)
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

        viewModel.favoriteResult.observe(viewLifecycleOwner) { result ->

            result.success?.let { favoriteModel ->
                setupDesignFavorite(binding, favoriteModel)
            }

            result.error?.let { error ->
                Toast.makeText(requireContext(), getString(error), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateDetail(binding: SecondFragmentBinding, model: DetailResponseModel) {
        val adapter = SecondAdapter(model.multimedia?.images)
        binding.viewPager.adapter = adapter

        // Vincular TabLayout con ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, _ ->
            val tabBinding = TabDotsBinding.inflate(LayoutInflater.from(requireContext()))
            tab.customView = tabBinding.root
        }.attach()
    }

    private fun setupView(binding: SecondFragmentBinding, model: ListResponseModel) {
        binding.tvName.text = model.district

        binding.tvType.text = model.neighborhood

        binding.tvLocation.text = model.address

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

        val currentRotation = binding.fabFavorite.rotation

        val rotateAnimator = model.id?.let {
            ObjectAnimator.ofFloat(binding.fabFavorite, "rotation", currentRotation, 45f).apply {
                duration = 300
            }
        } ?: run {
            ObjectAnimator.ofFloat(binding.fabFavorite, "rotation", currentRotation, 0f).apply {
                duration = 300
            }
        }
        
        rotateAnimator.start()

        model.id?.let {
            rotateAnimator.start()
            binding.fabFavorite.backgroundTintList = (ContextCompat.getColorStateList(requireContext(),android.R.color.holo_red_dark))
            model.dateAdded?.let { date ->
                updateDate(binding, date)
            }
        } ?: run {
            rotateAnimator.start()
            binding.fabFavorite.backgroundTintList = (ContextCompat.getColorStateList(requireContext(),android.R.color.holo_orange_dark))
            binding.tvDateAdded.visibility = View.GONE
        }
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