package com.example.nexos_credibanco.ui.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.data.model.AuthorizationVo
import com.example.nexos_credibanco.data.model.CancelVo
import com.example.nexos_credibanco.databinding.FindTransactionFragmentBinding
import com.example.nexos_credibanco.ui.adapters.FindTransactionAdapter
import com.example.nexos_credibanco.ui.interfaces.ICanceItem
import com.example.nexos_credibanco.ui.viewmodels.AuthorizationViewModel
import java.util.Base64

class FindTransactionFragment : Fragment(), ICanceItem<AuthorizationResponseVo> {

    private lateinit var _binding: FindTransactionFragmentBinding
    private val binding get() = _binding
    private val viewModel: AuthorizationViewModel by viewModels { AuthorizationViewModel.AuthorizationViewModelFactory(requireActivity().application) }
    private var transactions = mutableListOf<AuthorizationResponseVo>()
    private var authorizationInfo = AuthorizationVo()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FindTransactionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFilterTransactions()
        observers()
        listeners()
    }

    private fun observers() {
        viewModel.getTransactions().observe(viewLifecycleOwner) { filteredTransactions ->
            transactions = filteredTransactions.toMutableList()
            binding.recyclerViewTransactions.adapter = FindTransactionAdapter(requireContext(), transactions, this)
            (binding.recyclerViewTransactions.adapter as FindTransactionAdapter).refresh()
            binding.textViewnothingTransactions.isVisible = transactions.isEmpty()
        }
    }

    private fun listeners() {
        binding.editTextFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filterName = transactions.filter { data -> data.receiptId.uppercase().contains(s.toString().uppercase()) }
                binding.recyclerViewTransactions.adapter = context?.let { FindTransactionAdapter(it, filterName.toMutableList(), this@FindTransactionFragment) }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCancelClick(item: AuthorizationResponseVo) {
        val cancelVo = CancelVo(
            receiptId = item.receiptId,
            rrn = item.rrn,
        )
        val authorizationCode =
            ("Basic ".plus(Base64.getEncoder().encodeToString((authorizationInfo.commerceCode + authorizationInfo.terminalCode).encodeToByteArray())))
        viewModel.getCancelTransaction(authorizationCode, cancelVo)
        transactions.remove(item)
        viewModel.updateTransactions(transactions)
    }


    companion object {

        fun newInstance(): FindTransactionFragment {
            return FindTransactionFragment().apply {
                arguments = Bundle()
            }
        }
    }

}