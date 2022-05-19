package com.albertomier.mydogcollection.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.albertomier.mydogcollection.R
import com.albertomier.mydogcollection.core.Utils
import com.albertomier.mydogcollection.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    interface SignUpFragmentActions {
        fun onSignUpFieldsValidates(email: String, password: String, passwordConfirmation: String)
    }

    private lateinit var signUpFragmentActions: SignUpFragmentActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signUpFragmentActions = try {
            context as SignUpFragmentActions
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implements LoginFragmentActions")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpSignUpButton()
        return root
    }

    private fun setUpSignUpButton() {
        binding.signUpButton.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        clearErrors()

        val email = binding.emailEdit.text.toString()
        val password = binding.passwordEdit.text.toString()
        val passwordConfirmation = binding.confirmPasswordEdit.text.toString()

        if (!Utils.isEmailValid(email)) {
            binding.emailInput.error = getString(R.string.invalid_email)
            return
        }
        if (password.isEmpty()) {
            binding.passwordInput.error = getString(R.string.invalid_password)
            return
        }
        if (passwordConfirmation.isEmpty()) {
            binding.confirmPasswordInput.error = getString(R.string.invalid_password)
            return
        }
        if (password != passwordConfirmation) {
            binding.confirmPasswordInput.error = getString(R.string.invalid_password_confirmation)
            return
        }

        signUpFragmentActions.onSignUpFieldsValidates(email, password, passwordConfirmation)
    }

    private fun clearErrors() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""
        binding.confirmPasswordInput.error = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}