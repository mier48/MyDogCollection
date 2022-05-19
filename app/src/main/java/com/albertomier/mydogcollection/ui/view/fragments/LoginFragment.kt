package com.albertomier.mydogcollection.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.albertomier.mydogcollection.R
import com.albertomier.mydogcollection.core.Utils
import com.albertomier.mydogcollection.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    interface LoginFragmentActions {
        fun onRegisterButtonClick()
        fun onSignInFieldsValidates(email: String, password: String)
    }

    private lateinit var loginFragmentActions: LoginFragmentActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginFragmentActions = try {
            context as LoginFragmentActions
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implements LoginFragmentActions")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.loginRegisterButton.setOnClickListener {
            loginFragmentActions.onRegisterButtonClick()
        }

        binding.loginButton.setOnClickListener {
            validateFields()
        }

        return root
    }

    private fun validateFields() {
        clearErrors()

        val email = binding.emailEdit.text.toString()
        val password = binding.passwordEdit.text.toString()

        if (!Utils.isEmailValid(email)) {
            binding.emailInput.error = getString(R.string.invalid_email)
            return
        }
        if (password.isEmpty()) {
            binding.passwordInput.error = getString(R.string.invalid_password)
            return
        }

        loginFragmentActions.onSignInFieldsValidates(email, password)
    }

    private fun clearErrors() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}