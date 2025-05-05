package com.android.basketballapp.login

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val auth = Firebase.auth
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Register callbacks
    LaunchedEffect(Unit) {
        LoginScreenHandler.onLoginSuccess = onLoginSuccess
        LoginScreenHandler.onError = { errorMessage = it }
    }

    // Background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1A2980), Color(0xFF26D0CE))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Ball Don't Lie", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener { onLoginSuccess() }
                            .addOnFailureListener { errorMessage = it.message }
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Login with Email", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                SocialLoginButton(
                    text = "Login with Google",
                    color = Color(0xFF4285F4),
                    onClick = { LoginScreenHandler.googleSignIn?.invoke() }
                )

                Spacer(modifier = Modifier.height(12.dp))

                SocialLoginButton(
                    text = "Login with GitHub",
                    color = Color(0xFF333333),
                    onClick = {
                        val provider = OAuthProvider.newBuilder("github.com")
                        auth.startActivityForSignInWithProvider(context as Activity, provider.build())
                            .addOnSuccessListener { onLoginSuccess() }
                            .addOnFailureListener { errorMessage = it.message }
                    }
                )

                errorMessage?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = it, color = Color.Red, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun SocialLoginButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 16.sp)
    }
}
