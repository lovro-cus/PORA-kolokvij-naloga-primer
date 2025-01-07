# Supabase Auth Demo App

Ta projekt prikazuje uporabo **Supabase Auth-KT knjižnice** za avtentikacijo uporabnikov v Android aplikaciji. Implementirani so osnovni funkcionalnosti, kot so **registracija**, **prijava** in **odjava** z uporabo **Ktor** odjemalca.

---

## 📚 Viri

- [Supabase Auth-KT GitHub Repository](https://github.com/supabase-community/supabase-kt/tree/master/Auth)
- [Uradna Supabase dokumentacija za Kotlin](https://supabase.com/docs/reference/kotlin/auth-signup)

---

## 🔧 Zahteve

- **Android Studio** 2021.1.1 ali novejši
- **Kotlin 1.8.0** ali novejši
- **Gradle verzija 8.0+**
- **Supabase API ključ** (pridobljen iz [Supabase nadzorne plošče](https://supabase.com))
- **Internetna povezava** za dostop do avtentikacijskih storitev

---

## 📦 Odvisnosti

```kotlin
dependencies {
    // Supabase Auth-KT knjižnice
    implementation("io.github.jan-tennert.supabase:auth-kt:3.0.3")
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    
    // Ktor za HTTP odjemalec
    implementation("io.ktor:ktor-client-android:3.0.2")

    // JSON serializacija za prenos podatkov
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}
