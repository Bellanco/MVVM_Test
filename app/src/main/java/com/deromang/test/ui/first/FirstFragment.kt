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
import com.deromang.test.databinding.FragmentFirstBinding
import com.deromang.test.model.ListResponseModel
import com.deromang.test.ui.first.adapter.FirstAdapter
import com.deromang.test.ui.first.adapter.FirstViewHolder

class FirstFragment : Fragment() {

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding = FragmentFirstBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

        setupObservables(binding)

        setupView(binding)

        setupAdapterCharacters(binding)

        viewModel.getListElements()

        return binding.root
    }

    private fun setupView(binding: FragmentFirstBinding) {
        binding.fabFilter.visibility = View.GONE
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

    private fun updateCharacters(binding: FragmentFirstBinding, modelList: MutableList<ListResponseModel>) {
        (binding.rvMain.adapter as? FirstAdapter)?.apply {
            addAll(modelList)
        }
    }

    private fun setupAdapterCharacters(binding: FragmentFirstBinding) {

        binding.rvMain.layoutManager = LinearLayoutManager(context)
        val actualityAdapter =
            FirstAdapter(requireContext(), object : FirstViewHolder.OnItemClickListener {
                override fun onClick(model: ListResponseModel) {
                    findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(model))
                }
            })

        binding.rvMain.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.rvMain.adapter = actualityAdapter
    }

}