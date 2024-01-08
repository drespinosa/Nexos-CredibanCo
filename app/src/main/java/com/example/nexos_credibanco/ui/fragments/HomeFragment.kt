package com.example.nexos_credibanco.ui.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.nexos_credibanco.R
import com.example.nexos_credibanco.data.model.AuthorizationVo
import com.example.nexos_credibanco.databinding.HomeFragmentBinding
import com.example.nexos_credibanco.extensions.setCorrectColors
import com.example.nexos_credibanco.ui.viewmodels.AuthorizationViewModel
import java.util.Base64

class HomeFragment : Fragment() {

    private lateinit var _binding: HomeFragmentBinding
    private val binding get() = _binding
    private val viewModel: AuthorizationViewModel by viewModels { AuthorizationViewModel.AuthorizationViewModelFactory(requireActivity().application) }
    private var authorizationInfo = AuthorizationVo()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }

    override fun onResume() {
        super.onResume()
        binding.checkBoxAuthorize.isChecked = false
        binding.includeFormAuthorization.constraintLayoutFormAuthorization.isVisible = false
        binding.includeFormAuthorization.buttonAuthorization.isVisible = false
        binding.viewLine.isVisible = false
        binding.textViewInformationTitle.isVisible = false
        binding.includeFormAuthorization.editTextCommerceCode.isEnabled = false
        binding.includeFormAuthorization.editTextCard.isEnabled = false
        binding.includeFormAuthorization.editTextTerminalCode.isEnabled = false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun listeners() {

        binding.checkBoxAuthorize.setOnClickListener {
            binding.checkBoxAuthorize.setCorrectColors(binding.checkBoxAuthorize.isChecked)
            if (binding.checkBoxAuthorize.isChecked) {
                binding.includeFormAuthorization.constraintLayoutFormAuthorization.isVisible = true
                binding.includeFormAuthorization.buttonAuthorization.isVisible = true
                binding.textViewInformationTitle.isVisible = true
                binding.viewLine.isVisible = true
                binding.includeFormAuthorization.editTextId.setText(authorizationInfo.id)
            } else {
                binding.includeFormAuthorization.constraintLayoutFormAuthorization.isVisible = false
                binding.includeFormAuthorization.buttonAuthorization.isVisible = false
                binding.textViewInformationTitle.isVisible = false
                binding.viewLine.isVisible = false
                binding.includeFormAuthorization.editTextId.setText("")
            }
        }

        binding.buttonFind.setOnClickListener {
            // navegar a la vista de buscar transacciones
            replaceFragment(FindTransactionFragment())
        }

        binding.buttonGet.setOnClickListener {
            // navegar a la vista de obtener transacciones
            replaceFragment(AllTransactionsFragment())
        }

        binding.includeFormAuthorization.editTextCard.setText(authorizationInfo.card)
        binding.includeFormAuthorization.editTextCommerceCode.setText(authorizationInfo.commerceCode)
        binding.includeFormAuthorization.editTextTerminalCode.setText(authorizationInfo.terminalCode)

        binding.includeFormAuthorization.editTextAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                if (!editable.isNullOrEmpty()) {
                    val originalString = editable.toString()
                    val cleanString = originalString.replace("[^\\d.]".toRegex(), "")
                    val value = cleanString.toDouble() / 100.0

                    val formattedString = String.format("%.2f", value)
                    binding.includeFormAuthorization.editTextAmount.removeTextChangedListener(this)
                    binding.includeFormAuthorization.editTextAmount.setText(formattedString)
                    binding.includeFormAuthorization.editTextAmount.setSelection(formattedString.length)
                    binding.includeFormAuthorization.editTextAmount.addTextChangedListener(this)
                    authorizationInfo.amount = editable.toString()
                }
            }
        })

        binding.includeFormAuthorization.buttonAuthorization.setOnClickListener {

            val amount = binding.includeFormAuthorization.editTextAmount.text.toString().trim()
            if (amount.isBlank()) {
                val errorMessage = "Falta añadir un valor para pagar"
                val errorBuilder = AlertDialog.Builder(requireContext())
                errorBuilder.setMessage(errorMessage)
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                val errorDialog = errorBuilder.create()
                errorDialog.show()
            } else {
                val authorizationCode =
                    ("Basic ".plus(Base64.getEncoder().encodeToString((authorizationInfo.commerceCode + authorizationInfo.terminalCode).encodeToByteArray())))
                viewModel.getAuthorizationTransaction(authorizationCode, getInformation())

                val message = "¡Autorización exitosa!"
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage(message)
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                        binding.includeFormAuthorization.editTextAmount.text?.clear()
                    }
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }
    }

    private fun getInformation(): AuthorizationVo {
        authorizationInfo.id = binding.includeFormAuthorization.editTextCommerceCode.text.toString()
        authorizationInfo.commerceCode = binding.includeFormAuthorization.editTextCommerceCode.text.toString()
        authorizationInfo.terminalCode = binding.includeFormAuthorization.editTextTerminalCode.text.toString()
        authorizationInfo.amount = binding.includeFormAuthorization.editTextAmount.text.toString()
        authorizationInfo.card = binding.includeFormAuthorization.editTextCard.text.toString()

        return AuthorizationVo(
            id = authorizationInfo.id,
            commerceCode = authorizationInfo.commerceCode,
            terminalCode = authorizationInfo.terminalCode,
            amount = authorizationInfo.amount,
            card = authorizationInfo.card,
        )

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment().apply {
                arguments = Bundle()
            }
        }
    }

}