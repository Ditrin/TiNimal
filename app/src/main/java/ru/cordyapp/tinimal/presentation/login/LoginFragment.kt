package ru.cordyapp.tinimal.presentation.login

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.AuthDTO
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserDTO
import ru.cordyapp.tinimal.databinding.FragmentLoginBinding
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val sharedPreferences = activity?.getSharedPreferences(NAME, MODE_PRIVATE)
//        val token = SharedPref.authToken

//        if (token != "None")
//            Toast.makeText(activity, token, Toast.LENGTH_SHORT).show()

        binding.signInButton.setOnClickListener {
            val user =
                AuthDTO(
                    binding.loginEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )

            viewModel.postAuthorization(user)

            viewModel.token.observe(viewLifecycleOwner) {
                Log.d("asd", "Token is $it")
                if (it != "") {
                    SharedPref.authToken = it
                }
                else
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
            viewModel.message.observe(viewLifecycleOwner) {
                if (it == "Success") {
                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                } else
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }


        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
        }
    }

    companion object {
        private const val AUTH_TOKEN = "auth_token"
        private const val NAME = "app_preferences"
    }

}