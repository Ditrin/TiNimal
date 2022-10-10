package ru.cordyapp.tinimal.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.databinding.FragmentLoginBinding
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!SharedPref.authToken.isNullOrBlank())
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

        viewModel.isEnabled.observe(viewLifecycleOwner){
            binding.signInButton.isEnabled = it
            binding.signUpButton.isEnabled = it
        }
        binding.signInButton.setOnClickListener {
            val user =
                AuthDTO(
                    binding.loginEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            viewModel.postAuthorization(user)
            viewModel.message.observe(viewLifecycleOwner) {
                if (it == "Success") {
                    SharedPref.authToken = viewModel.token
                    SharedPref.id = viewModel.id
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_authFragment)
        }
    }
}