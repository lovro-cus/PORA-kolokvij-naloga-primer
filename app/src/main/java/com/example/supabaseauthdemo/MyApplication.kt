package com.example.supabaseauthdemo

import android.app.Application
import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient

class MyApplication : Application() {

    lateinit var supabaseClient: SupabaseClient
        private set

    override fun onCreate() {
        Log.d("APP","APP onCreate")
        super.onCreate()

        supabaseClient = createSupabaseClient(
            supabaseUrl = "https://mwvrfcczfirraknqnslj.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im13dnJmY2N6ZmlycmFrbnFuc2xqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzYyNjgxNTAsImV4cCI6MjA1MTg0NDE1MH0._aWYU_L15u9AmwuRqRipLwEj2jISIIrbbnjlDeV3YSQ"
        ) {
            install(Auth)
            install(Postgrest)
        }

    }
}
