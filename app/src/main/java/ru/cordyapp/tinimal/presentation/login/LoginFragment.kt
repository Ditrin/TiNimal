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

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = activity?.getSharedPreferences("MY_SHARED_PREFS", MODE_PRIVATE)
        val token = sharedPreferences?.getString("TOKEN", "None")

        if (token != "None")
            Toast.makeText(activity, token, Toast.LENGTH_SHORT).show()

        binding.signInButton.setOnClickListener {
            val user =
                AuthDTO(
                    binding.loginEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )

            viewModel.postAuthorization(user)
            Thread.sleep(1000)

            viewModel.token.observe(viewLifecycleOwner) {
                Log.d("asd", "Token is $it")
                if (it != "") {
                    val myEdit = sharedPreferences?.edit()

                    myEdit?.putString("TOKEN", it)
                    myEdit?.apply()
                }
                else
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
            viewModel.message.observe(viewLifecycleOwner) {
                if (it == "") {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }


        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_authFragment)
        }
    }

    companion object {
        private const val PREF = "MY_SHARED_PREFS"
    }

}