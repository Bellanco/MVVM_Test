package com.deromang.test.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.deromang.test.R
import com.deromang.test.databinding.SecondFragmentBinding
import com.deromang.test.model.Result
import com.deromang.test.util.setImageUrl
import com.deromang.test.util.share

class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding = SecondFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        args.model?.let { model : Result ->

                binding.tvName.text = """${model.name.title} ${model.name.title} ${model.name.last}"""
                binding.ivDetail.setImageUrl(requireContext(), model.picture.large)
                binding.tvLocation.text = model.location.city
                binding.tvType.text = model.location.state

                binding.tvLocation.text = getString(R.string.label_location, model.location.street.name)

                binding.fabShare.setOnClickListener {
                    share(requireContext(), model.picture.large)
                }
        }

        return binding.root
    }


}