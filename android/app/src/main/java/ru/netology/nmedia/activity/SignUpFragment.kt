package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentSignUpBinding
import ru.netology.nmedia.viewmodel.AuthViewModel
import kotlin.coroutines.EmptyCoroutineContext

class SignUpFragment : DialogFragment() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)

        authViewModel.state.observe(viewLifecycleOwner) {
            binding.registerButton.setOnClickListener {
                if (binding.username.text.isBlank() || binding.login.text.isBlank() ||
                    binding.password.text.isBlank() || binding.repeatPassword.text.isBlank()) {
                    Toast.makeText(context, R.string.error_blank_fields, Toast.LENGTH_LONG).show()
                } else if (binding.password.text.toString() != binding.repeatPassword.text.toString()) {
                    Toast.makeText(context, R.string.error_passwords, Toast.LENGTH_LONG).show()
                } else {
                    with(CoroutineScope(EmptyCoroutineContext)) {
                        launch {
                            authViewModel.registerUser(
                                binding.login.text.toString(),
                                binding.password.text.toString(),
                                binding.username.text.toString()
                            ).join()

                            if (authViewModel.authorized) {
                                dismiss()

                            }
                        }
                    }
                }
            }
        }

        return binding.root
    }
}