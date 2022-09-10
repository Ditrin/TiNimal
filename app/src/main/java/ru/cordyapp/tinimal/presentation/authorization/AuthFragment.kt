package ru.cordyapp.tinimal.presentation.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.data.remote.DTOmodels.UserAuthDTO
import ru.cordyapp.tinimal.databinding.FragmentAuthBinding

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            createButtonAuthFragment.setOnClickListener {
                viewModel.verify(
                    loginEditTextAuthFragment.text.toString(),
                    passwordEditTextAuthFragment.text.toString(),
                    nameEditTextAuthFragment.text.toString(),
                    numberEditTextAuthFragment.text.toString(),
                    mailEditTextAuthFragment.text.toString(),
                    addressEditTextAuthFragment.text.toString()
                )
                viewModel.isValidate.observe(viewLifecycleOwner) {
                    if (it) {
                        errorTextView.visibility = View.INVISIBLE
                        val user = UserAuthDTO(
                            loginEditTextAuthFragment.text.toString(),
                            passwordEditTextAuthFragment.text.toString(),
                            nameEditTextAuthFragment.text.toString(),
                            numberEditTextAuthFragment.text.toString().toLong(),
                            mailEditTextAuthFragment.text.toString(),
                            addressEditTextAuthFragment.text.toString()
                        )
                        viewModel.createUser(user)
                        findNavController().navigate(R.id.action_authFragment_to_loginFragment)
                    } else
                        errorTextView.visibility = View.VISIBLE
                }
            }
        }
    }

}