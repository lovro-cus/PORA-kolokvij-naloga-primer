package com.example.moneymind

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.supabaseauthdemo.LogInFragment
import com.example.supabaseauthdemo.MyApplication
import com.example.supabaseauthdemo.R
import com.example.supabaseauthdemo.databinding.FragmentRegisterBinding
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val supabaseClient by lazy {
        (requireContext().applicationContext as MyApplication).supabaseClient
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerButton.setOnClickListener{
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

//            if(email.isEmpty() || password.isEmpty()){
//                Toast.makeText(requireContext(), "Insert data", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    supabaseClient.auth.signUpWith(Google){

                    }
                    Toast.makeText(requireContext(),"Registration Successful!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                } catch (e: Exception){
                    Toast.makeText(requireContext(), "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.d("FAILED REG", e.toString())
                }
            }

        }
        binding.loginTextView.setOnClickListener {
            val logInFragment = LogInFragment()
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    logInFragment
               ).addToBackStack(null)
                .commit()
       }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}