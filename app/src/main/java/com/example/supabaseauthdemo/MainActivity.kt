package com.example.supabaseauthdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.moneymind.RegisterFragment
import com.example.supabaseauthdemo.databinding.ActivityMainBinding
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val supabaseClient by lazy {
        (application as MyApplication).supabaseClient
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(isUserLoggedIn()){
            replaceFragment(TestFragment())
        }else{
            replaceFragment(LogInFragment())
        }


        if(isUserLoggedIn()){
            binding.floatingActionButton2.setOnClickListener {
                //replaceFragment(MainFragment())
            }
            binding.floatingActionButton.setOnClickListener {
                lifecycleScope.launch {
                    try {
                        supabaseClient.auth.signOut()
                    }catch (e: Exception){
                        Toast.makeText(this@MainActivity, "Sign out error", Toast.LENGTH_SHORT).show()
                    }
                    replaceFragment(RegisterFragment())
                }
                updateUI()
                //replaceFragment(AddFragment())
            }
        }else{
            binding.floatingActionButton.setImageResource(R.drawable.sign_in)
            binding.floatingActionButton2.setImageResource(R.drawable.registration)

            binding.floatingActionButton.setOnClickListener {
                replaceFragment(LogInFragment())
            }
            binding.floatingActionButton2.setOnClickListener {
                replaceFragment(RegisterFragment())
            }
        }


    }

    fun updateUI() {
        if (isUserLoggedIn()) {
            // Prijavljen uporabnik
            binding.floatingActionButton.setImageResource(R.drawable.plus)
            binding.floatingActionButton2.setImageResource(R.drawable.home)

            binding.floatingActionButton.setOnClickListener {
                lifecycleScope.launch {
                    try {
                        supabaseClient.auth.signOut()
                    }catch (e: Exception){
                        Toast.makeText(this@MainActivity, "Sign out error", Toast.LENGTH_SHORT).show()
                    }
                    replaceFragment(RegisterFragment())
                }
                updateUI()
            }
            binding.floatingActionButton2.setOnClickListener {
                //replaceFragment(MainFragment())
            }
        } else {
            // Neprijavljen uporabnik
            binding.floatingActionButton.setImageResource(R.drawable.sign_in)
            binding.floatingActionButton2.setImageResource(R.drawable.registration)

            binding.floatingActionButton.setOnClickListener {
                //replaceFragment(LogInFragment())
            }
            binding.floatingActionButton2.setOnClickListener {
                //replaceFragment(RegisterFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
    private fun isUserLoggedIn(): Boolean {
        val session = supabaseClient.auth.currentSessionOrNull()
        return session != null
    }
}