package com.android.basketballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.android.basketballapp.login.LoginScreenHandler
import com.android.basketballapp.presentation.games.GamesViewModel
import com.android.basketballapp.presentation.navigation.AppNavGraph
import com.android.basketballapp.presentation.players.PlayersViewModel
import com.android.basketballapp.teams.TeamsViewModel
import com.android.basketballapp.ui.theme.BasketballAppTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val gamesViewModel: GamesViewModel by viewModels()
    private val playersViewModel: PlayersViewModel by viewModels()
    private val teamsViewModel: TeamsViewModel by viewModels()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var githubProvider: OAuthProvider.Builder

    // ✅ Google Sign-In Launcher
    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(Exception::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                auth.signInWithCredential(credential)
                    .addOnSuccessListener {
                        LoginScreenHandler.onLoginSuccess?.invoke()
                    }
                    .addOnFailureListener {
                        LoginScreenHandler.onError?.invoke(it.message ?: "Google Sign-In failed.")
                    }
            } catch (e: Exception) {
                LoginScreenHandler.onError?.invoke(e.message ?: "Google Sign-In error.")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // from google-services.json
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        // ✅ GitHub OAuth setup with scopes
        githubProvider = OAuthProvider.newBuilder("github.com").apply {
            addCustomParameter("allow_signup", "true") // optional
            scopes = listOf("read:user", "user:email")
        }

        // ✅ Expose sign-in handlers to composable
        LoginScreenHandler.googleSignIn = {
            val intent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(intent)
        }

        LoginScreenHandler.githubSignIn = {
            auth
                .startActivityForSignInWithProvider(this, githubProvider.build())
                .addOnSuccessListener {
                    LoginScreenHandler.onLoginSuccess?.invoke()
                }
                .addOnFailureListener {
                    LoginScreenHandler.onError?.invoke(it.message ?: "GitHub Sign-In failed.")
                }
        }

        setContent {
            BasketballAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavGraph(
                        navController = navController,
                        gamesViewModel = gamesViewModel,
                        playersViewModel = playersViewModel,
                        teamsViewModel = teamsViewModel,
                        startDestination = if (auth.currentUser == null) "login" else "home"
                    )
                }
            }
        }
    }
}
