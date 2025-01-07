package com.example.supabaseauthdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moneymind.RegisterFragment
import com.example.supabaseauthdemo.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(isUserLoggedIn()){
            //replaceFragment(MainFragment())
        }else{
            replaceFragment(LogInFragment())
        }


        if(isUserLoggedIn()){
            binding.floatingActionButton2.setOnClickListener {
                //replaceFragment(MainFragment())
            }
            binding.floatingActionButton.setOnClickListener {
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
                //replaceFragment(AddFragment())
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
        //val session = supabaseClient.auth.currentSessionOrNull()
        return false
    }
}