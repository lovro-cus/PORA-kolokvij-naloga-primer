package com.example.supabaseauthdemo

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import com.example.supabaseauthdemo.databinding.FragmentLogInBinding
import com.example.supabaseauthdemo.databinding.FragmentRegisterBinding
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import kotlinx.coroutines.launch



class LogInFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private val supabaseClient by lazy {
        (requireContext().applicationContext as MyApplication).supabaseClient
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.loginButton.setOnClickListener{
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Insert data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    supabaseClient.auth.signInWith(Email) {
                        this.email = email
                        this.password = password
                    }
                    Toast.makeText(requireContext(), "LogIn successful", Toast.LENGTH_SHORT).show()
                    (requireActivity() as? MainActivity)?.updateUI()
                    changeFragment(TestFragment())
                    //showLoginNotification()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "LogIn error: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.d("ERROR SIGN IN", e.message.toString())
                }
            }

        }

    }



    private fun changeFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}