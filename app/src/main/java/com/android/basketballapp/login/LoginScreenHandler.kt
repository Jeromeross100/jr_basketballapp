package com.android.basketballapp.login


/**
 * Provides hooks for triggering Google and GitHub sign-in from Composables
 * and handling authentication results.
 */
object LoginScreenHandler {

    // Call this from LoginScreen when user taps "Sign in with Google"
    var googleSignIn: (() -> Unit)? = null

    // Call this from LoginScreen when user taps "Sign in with GitHub"
    var githubSignIn: (() -> Unit)? = null

    // Assign this in LoginScreen to handle success (e.g., navigate to Home)
    var onLoginSuccess: (() -> Unit)? = null

    // Assign this in LoginScreen to display error messages
    var onError: ((String) -> Unit)? = null

    // Optional cleanup method
    fun clear() {
        googleSignIn = null
        githubSignIn = null
        onLoginSuccess = null
        onError = null
    }
}
