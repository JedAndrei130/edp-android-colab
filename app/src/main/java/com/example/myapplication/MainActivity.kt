package com.example.myapplication

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.toArgb
import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import coil.compose.AsyncImage

@Composable
fun BusinessCard() {
    val isDarkTheme = isSystemInDarkTheme()
    val view = LocalView.current
    
    // Ensure status bar icons (Wi-Fi, etc.) are visible
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }

    // Recognizable themes: Solid Dark Gray for Dark Mode, Soft Blue-Gray Gradient for Light Mode
    val backgroundModifier = if (isDarkTheme) {
        Modifier.background(Color(0xFF121212)) // Solid Dark Gray (Near Black)
    } else {
        Modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFFFFF), // Pure White
                    Color(0xFFE0E6ED)  // Soft Blue-Gray
                )
            )
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(backgroundModifier),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Main Content Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isDarkTheme) Color(0xFF1E1E1E) else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (isDarkTheme) 0.dp else 12.dp
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isDarkTheme) Color.White.copy(alpha = 0.08f) else Color.Black.copy(alpha = 0.05f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 32.dp, vertical = 44.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Styled Avatar with coordinated border
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(140.dp)
                                .border(
                                    2.dp, 
                                    if (isDarkTheme) Color(0xFF4DD0E1).copy(alpha = 0.5f) else Color(0xFF00ACC1).copy(alpha = 0.3f), 
                                    CircleShape
                                )
                                .padding(6.dp)
                                .clip(CircleShape)
                        ) {
                            AsyncImage(
                                model = "https://lh3.googleusercontent.com/a-/ALV-UjVq-C9vFH-LYPsj4Z6MRaV24iqcfqbXPwTzPacAoaaxgigDeQA-=s240-p-k-rw-no",
                                contentDescription = "Profile Picture",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(28.dp))

                        Text(
                            text = "Jed Andrei Surabasquez",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (isDarkTheme) Color.White else Color(0xFF1A1C1E),
                            letterSpacing = (-0.5).sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Android UI Architect",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isDarkTheme) Color(0xFF4DD0E1) else Color(0xFF007A8C),
                            letterSpacing = 1.sp,
                            modifier = Modifier
                                .background(
                                    (if (isDarkTheme) Color(0xFF4DD0E1) else Color(0xFF007A8C)).copy(alpha = 0.12f),
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                        )

                        Spacer(modifier = Modifier.height(44.dp))

                        // Coordinated Contact Section
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            ContactRow(
                                icon = Icons.Default.Phone,
                                text = "+63 950 218 0674",
                                tintColor = if (isDarkTheme) Color(0xFF81C784) else Color(0xFF2E7D32)
                            )
                            ContactRow(
                                icon = Icons.Default.Email,
                                text = "jed.surabasquez@android.dev",
                                tintColor = if (isDarkTheme) Color(0xFF64B5F6) else Color(0xFF1976D2)
                            )
                            ContactRow(
                                icon = Icons.Default.Share,
                                text = "github.com/jasurabasquez",
                                tintColor = if (isDarkTheme) Color(0xFFFFB74D) else Color(0xFFF57C00)
                            )
                        }
                    }
                }
            }
        }
    }
}

// Reusable Contact Row
@Composable
fun ContactRow(
    icon: ImageVector,
    text: String,
    tintColor: Color,
    onClickLabel: String? = null
) {
    val isDarkTheme = isSystemInDarkTheme()
    
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClickLabel = onClickLabel,
                role = Role.Button,
                onClick = { /* Action Stub */ }
            )
            .background(
                if (isDarkTheme) Color(0xFF2C2F33) else Color(0xFFF8F9FA)
            )
            .border(
                width = 1.dp,
                color = tintColor.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(tintColor.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tintColor,
                modifier = Modifier.size(18.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (isDarkTheme) Color(0xFFE0E0E0) else Color(0xFF1A1C1E),
            letterSpacing = 0.2.sp
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun BusinessCardPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        BusinessCard()
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun BusinessCardDarkPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        BusinessCard()
    }
}

@Preview(name = "Large Font Scale", fontScale = 1.5f)
@Composable
fun BusinessCardFontScalePreview() {
    MaterialTheme {
        BusinessCard()
    }
}