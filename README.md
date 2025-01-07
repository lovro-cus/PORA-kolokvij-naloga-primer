# Supabase Auth Demo App

Ta projekt prikazuje uporabo **Supabase Auth-KT knjižnice** za avtentikacijo uporabnikov v Android aplikaciji. Implementirani so osnovne funkcionalnosti, kot so **registracija**, **prijava** in **odjava** z uporabo **Ktor** odjemalca.

---

## 📚 Viri

- [Supabase Auth-KT GitHub Repository](https://github.com/supabase-community/supabase-kt/tree/master/Auth)
- [Uradna Supabase dokumentacija za Kotlin](https://supabase.com/docs/reference/kotlin/auth-signup)

---

## 🔧 Zahteve

- **Supabse 3.0.0** ali novejše
- **Kotlin 1.8.0** ali novejši
- **Gradle verzija 8.0+**
- **Supabase API ključ** (pridobljen iz [Supabase nadzorne plošče](https://supabase.com))
- **Internetna povezava** 

---

## 📦 Odvisnosti

```kotlin
dependencies {
    implementation("io.github.jan-tennert.supabase:auth-kt:VERSION")
}
```
##  ⬇️ Prenos
```kotlin
val supabase = createSupabaseClient(
    supabaseUrl = "https://id.supabase.co",
    supabaseKey = "apikey"
) {

    //...

    install(Auth) {
        // settings
    }

}
```

## 🔑 Dovoljenja

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"  
    xmlns:tools="http://schemas.android.com/tools">
    
        -
        -
        -
                
    <uses-permission android:name="android.permission.INTERNET"/>

        -
        -
        -
        
</manifest>
```


## ❓ Zakaj?

- **Brez potrebe po lastnem strežniku za avtentikacijo**
- **Več načinov avtentikacije**
    - 

## UPORABA

- **Dodamo novega uporabnika [Register]**
```kotlin
val user = supabase.auth.signUpWith(Email) {
    email = "example@email.com"
    password = "example-password"
}

val user = supabase.auth.signUpWith(Email) {
    email = "example@email.com"
    password = "example-password"
    data = buildJsonObject {
        put("first_name", "John")
        put("age", 24)
    }
}

```

- **Prijavimo uporabnika [Log in]**
```kotlin
supabase.auth.signInWith(Email) {
    email = "example@email.com"
    password = "example-password"
}


```
- **Odjavimo uporabnika [Sign out]**
```kotlin
supabase.auth.signOut()
```

- **Spremenimo podatke uporabniku**
```kotlin
val user = supabase.auth.updateUser {
    email = "newEmail@email.com"
}

val user = supabase.auth.updateUser {
    password = "secretPassword"
}

val user = supabase.auth.updateUser {
    data {
        put("name", "John")
    }
}
```

- **Preverimo če je uporabnik prijavljen**
```kotlin
val session = supabase.auth.currentSessionOrNull()
```

- **Pridobimu uporabnika iz trenutne seje**
```kotlin
val user = supabase.auth.retrieveUserForCurrentSession(updateSession = true)

``````
## Admin
*Kot admin lahko sami upravljamo uporabnike, nekaj osnovnih funkcij:*
