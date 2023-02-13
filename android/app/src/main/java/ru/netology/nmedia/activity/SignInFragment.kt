package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import okhttp3.internal.wait
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentSignInBinding
import ru.netology.nmedia.viewmodel.AuthViewModel

class SignInFragment : DialogFragment() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignInBinding.inflate(inflater, container, false)

        authViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }

        authViewModel.state.observe(viewLifecycleOwner) {
            binding.authorizeButton.setOnClickListener {
                if (binding.login.text.isBlank() && binding.password.text.isBlank()) {
                    Toast.makeText(context, R.string.error_blank_auth, Toast.LENGTH_LONG).show()
                } else if (binding.login.text.isBlank()) {
                    Toast.makeText(context, R.string.error_blank_username, Toast.LENGTH_LONG).show()
                } else if (binding.password.text.isBlank()) {
                    Toast.makeText(context, R.string.error_blank_password, Toast.LENGTH_LONG).show()
                } else {
                    authViewModel.updateUser(
                        binding.login.text.toString(),
                        binding.password.text.toString()
                    )

                    if (authViewModel.authorized) {
                        findNavController().navigateUp()
                    } else {
                        Toast.makeText(context, R.string.incorrect_credentials, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

        return binding.root
    }
}