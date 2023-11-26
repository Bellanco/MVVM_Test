package com.deromang.test.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.deromang.test.R
import com.deromang.test.data.Constants
import com.deromang.test.databinding.FragmentFirstBinding
import com.deromang.test.model.CharactersResponseModel
import com.deromang.test.model.Result
import com.deromang.test.ui.first.adapter.FirstAdapter
import com.deromang.test.ui.first.adapter.FirstViewHolder
import com.deromang.test.util.hideKeyboard

class FirstFragment : Fragment() {

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding = FragmentFirstBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

        setupObservables(binding)

        setupView(binding)

        setupAdapterCharacters(binding)

        viewModel.getCharacters()

        return binding.root
    }

    private fun setupView(binding: FragmentFirstBinding) {

        binding.fabFilter.setOnClickListener {
            binding.gpFilter.visibility =
                if (binding.gpFilter.visibility == View.GONE)
                    View.VISIBLE
                else
                    View.GONE
        }

        binding.btFilter.setOnClickListener {

            this@FirstFragment.hideKeyboard()

            binding.gpFilter.visibility = View.GONE

            val gender: String? =
                when (binding.rgState.checkedRadioButtonId) {
                    R.id.rbMale -> {
                        Constants.Status.KEY_MALE
                    }
                    R.id.rbFemale -> {
                        Constants.Status.KEY_FEMALE
                    }
                    R.id.rbNo -> {
                        null
                    }
                    else -> null
                }

            viewModel.getCharacters(gender)
        }
    }

    private fun setupObservables(binding: FragmentFirstBinding) {

        viewModel.getCharacterResult.observe(viewLifecycleOwner) { result ->
            result.success?.let { model ->
                updateCharacters(binding, model)
            }
            result.error?.let { error ->
                Toast.makeText(requireContext(), getString(error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCharacters(binding: FragmentFirstBinding, model: CharactersResponseModel) {
        (binding.rvMain.adapter as FirstAdapter).apply {
            addAll(model.results)
        }
    }

    private fun setupAdapterCharacters(binding: FragmentFirstBinding) {

        binding.rvMain.layoutManager = LinearLayoutManager(context)
        val actualityAdapter =
            FirstAdapter(requireContext(), object : FirstViewHolder.OnItemClickListener {
                override fun onClick(model: Result) {
                    findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(model))
                }
            })

        binding.rvMain.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.rvMain.adapter = actualityAdapter
    }

}