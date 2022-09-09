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
import ru.cordyapp.tinimal.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.postAuthorization(
               login, password
            )
            viewModel.message.observe(viewLifecycleOwner) {
                if (it == null)
                    Toast.makeText(activity, viewModel.token, Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }


        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_authFragment)
        }
    }


}