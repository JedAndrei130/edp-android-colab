package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    // Task 1 — Root Column with centered vertical arrangement & horizontal alignment
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Unified container (Background Box) for the whole profile content
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Task 2 — Circular Avatar
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .border(2.dp, MaterialTheme.colorScheme.onPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JA",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Task 3 — Full Name & Subtitle
                Text(
                    text = "Jed Andrei C. Surabasquez",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "BSIT 3-1",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Task 4 — The Info Section (Individual Boxes)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Task 5 — Reusable InfoRow called 5 times in specified order
                    InfoRow(
                        icon = Icons.Default.Person,
                        label = "Full Name",
                        value = "Jed Andrei C. Surabasquez"
                    )
                    InfoRow(
                        icon = Icons.Default.School,
                        label = "Course",
                        value = "BS in Information Technology (Liceo de Cagayan University)"
                    )
                    InfoRow(
                        icon = Icons.Default.Class,
                        label = "Section",
                        value = "3-1"
                    )
                    InfoRow(
                        icon = Icons.Default.Phone,
                        label = "Mobile Number",
                        value = "09502180674"
                    )
                    InfoRow(
                        icon = Icons.Default.Email,
                        label = "Email Address",
                        value = "jasurabasquez66130@liceo.edu.ph"
                    )
                }
            }
        }
    }
}

// Task 5 — Reusable InfoRow Composable (now with its own box style)
@Composable
fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

// Task 6 — Light & Dark Mode Previews
@Preview(name = "Profile — Light", showBackground = true)
@Composable
fun ProfileScreenLightPreview() {
    MyApplicationTheme(darkTheme = false) {
        Surface {
            ProfileScreen()
        }
    }
}

@Preview(
    name = "Profile — Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ProfileScreenDarkPreview() {
    MyApplicationTheme(darkTheme = true) {
        Surface {
            ProfileScreen()
        }
    }
}
