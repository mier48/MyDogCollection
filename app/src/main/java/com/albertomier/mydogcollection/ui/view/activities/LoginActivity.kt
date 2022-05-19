package com.albertomier.mydogcollection.ui.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.albertomier.mydogcollection.R
import com.albertomier.mydogcollection.core.Utils
import com.albertomier.mydogcollection.data.network.ApiResponseStatus
import com.albertomier.mydogcollection.databinding.ActivityDogDetailBinding
import com.albertomier.mydogcollection.databinding.ActivityLoginBinding
import com.albertomier.mydogcollection.domain.model.User
import com.albertomier.mydogcollection.ui.view.fragments.LoginFragment
import com.albertomier.mydogcollection.ui.view.fragments.LoginFragmentDirections
import com.albertomier.mydogcollection.ui.view.fragments.SignUpFragment
import com.albertomier.mydogcollection.ui.viewmodel.AuthViewModel
import com.albertomier.mydogcollection.ui.viewmodel.DogListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentActions, SignUpFragment.SignUpFragmentActions {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.status.observe(this) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    binding.progress.isVisible = false
                    Utils.showErrorDialog(R.string.information, status.messageId, this@LoginActivity)
                }
                is ApiResponseStatus.Loading -> binding.progress.isVisible = true
                is ApiResponseStatus.Success -> binding.progress.isVisible = false
            }
        }

        authViewModel.user.observe(this) { user ->
            if (user != null) {
                User.setLoggedInUser(user)
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onRegisterButtonClick() {
        findNavController(R.id.nav_host_fragment).navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
    }

    override fun onSignInFieldsValidates(email: String, password: String) {
        authViewModel.toSignIn(email, password)
    }

    override fun onSignUpFieldsValidates(email: String, password: String, passwordConfirmation: String) {
        authViewModel.toSignUp(email, password, passwordConfirmation)
    }
}