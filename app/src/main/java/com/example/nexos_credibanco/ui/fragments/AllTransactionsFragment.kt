package com.example.nexos_credibanco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.databinding.AllTransactionsFragmentBinding
import com.example.nexos_credibanco.extensions.setCorrectColors
import com.example.nexos_credibanco.ui.adapters.AllTransactionAdapter
import com.example.nexos_credibanco.ui.viewmodels.AuthorizationViewModel

class AllTransactionsFragment : Fragment() {

    private lateinit var _binding: AllTransactionsFragmentBinding
    private val binding get() = _binding
    private val viewModel: AuthorizationViewModel by viewModels { AuthorizationViewModel.AuthorizationViewModelFactory(requireActivity().application) }
    private var transactions = mutableListOf<AuthorizationResponseVo>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = AllTransactionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchTransactions()
        observers()
        listeners()
    }

    override fun onResume() {
        super.onResume()
        binding.checkBoxAprobedTransaction.isChecked = true
        binding.checkBoxCancelTransaction.isChecked = true
        binding.checkBoxAprobedTransaction.setCorrectColors(binding.checkBoxCancelTransaction.isChecked)
        binding.checkBoxCancelTransaction.setCorrectColors(binding.checkBoxCancelTransaction.isChecked)
    }

    private fun observers() {
        viewModel.getTransactions().observe(viewLifecycleOwner) {
            transactions = it.toMutableList()
            binding.recyclerViewTransactions.adapter = AllTransactionAdapter(requireContext(), transactions)
            (binding.recyclerViewTransactions.adapter as AllTransactionAdapter).refresh()
            binding.textViewnothingTransactions.isVisible = transactions.isEmpty()
        }
    }

    private fun listeners() {
        binding.checkBoxAprobedTransaction.setOnClickListener {
            binding.checkBoxAprobedTransaction.setCorrectColors(binding.checkBoxAprobedTransaction.isChecked)
            updateRecyclerView()
        }

        binding.checkBoxCancelTransaction.setOnClickListener {
            binding.checkBoxCancelTransaction.setCorrectColors(binding.checkBoxCancelTransaction.isChecked)
            updateRecyclerView()
        }
    }

    /**
     * Método que actualiza dinámicamente el RecyclerView en función de los estados de dos checkboxes,
     * filtrando y mostrando las transacciones según la combinación de selecciones.
     */
    private fun updateRecyclerView() {
        val showAprobed = binding.checkBoxAprobedTransaction.isChecked
        val showCancelled = binding.checkBoxCancelTransaction.isChecked

        val filteredList = when {
            showAprobed && showCancelled -> transactions.toMutableList()
            showAprobed -> transactions.filter { it.enable }.toMutableList()
            showCancelled -> transactions.filter { !it.enable }.toMutableList()
            else -> mutableListOf()
        }

        binding.recyclerViewTransactions.adapter = AllTransactionAdapter(requireContext(), filteredList)
        (binding.recyclerViewTransactions.adapter as AllTransactionAdapter).refresh()

    }


    companion object {

        fun newInstance(): AllTransactionsFragment {
            return AllTransactionsFragment().apply {
                arguments = Bundle()
            }
        }
    }

}