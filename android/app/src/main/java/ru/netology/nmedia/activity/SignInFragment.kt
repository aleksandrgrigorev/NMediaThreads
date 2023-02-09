package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentSignInBinding
import ru.netology.nmedia.viewmodel.AuthViewModel

class SignInFragment : DialogFragment() {

    val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.authorizeButton.setOnClickListener {
            if (binding.editUsername.text.isNotBlank() && binding.editPassword.text.isNotBlank()) {
                authViewModel.updateUser(
                    binding.editUsername.toString(),
                    binding.editPassword.toString()
                )
            } else {
                Toast.makeText(context, R.string.error_auth, Toast.LENGTH_LONG).show()
            }
        }

        authViewModel.state.observe(viewLifecycleOwner) {
            if (authViewModel.authorized) {
                findNavController().navigateUp()
            }
        }

        return binding.root
    }
}