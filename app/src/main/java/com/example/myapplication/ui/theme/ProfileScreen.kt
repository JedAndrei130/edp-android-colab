package com.example.myapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileForm(
    state: ProfileUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onContactChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onNewSkillChange: (String) -> Unit,
    addSkill: () -> Unit,
    removeSkill: (String) -> Unit,
    showPreview: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Edit Profile", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    "Personal Information",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                ProfileTextField(
                    value = state.name,
                    onValueChange = onNameChange,
                    label = "Full Name",
                    icon = Icons.Default.Person
                )
            }

            item {
                ProfileTextField(
                    value = state.email,
                    onValueChange = onEmailChange,
                    label = "Email Address",
                    icon = Icons.Default.Email
                )
            }

            item {
                ProfileTextField(
                    value = state.contactNumber,
                    onValueChange = onContactChange,
                    label = "Contact Number",
                    icon = Icons.Default.Phone
                )
            }

            item {
                ProfileTextField(
                    value = state.address,
                    onValueChange = onAddressChange,
                    label = "Address",
                    icon = Icons.Default.LocationOn
                )
            }

            item {
                ProfileTextField(
                    value = state.username,
                    onValueChange = onUsernameChange,
                    label = "Username",
                    icon = Icons.Default.AccountCircle
                )
            }

            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    "Skills & Expertise",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = state.newSkill,
                        onValueChange = onNewSkillChange,
                        label = { Text("Add a skill") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = addSkill,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add Skill",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }

            items(state.skills) { skill ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            skill,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        IconButton(onClick = { removeSkill(skill) }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Remove",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = showPreview,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Icon(Icons.Default.Visibility, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("View Profile Preview", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePreview(state: ProfileUiState, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile Preview", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Header
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = if (state.name.isBlank()) "No Name Provided" else state.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "@${if (state.username.isBlank()) "username" else state.username}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(32.dp))

            // Info Card
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InfoRow(icon = Icons.Default.Email, label = "Email", value = state.email)
                    InfoRow(icon = Icons.Default.Phone, label = "Contact", value = state.contactNumber)
                    InfoRow(icon = Icons.Default.LocationOn, label = "Address", value = state.address)
                }
            }

            Spacer(Modifier.height(24.dp))

            // Skills Section
            Text(
                "Skills & Expertise",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            
            Spacer(Modifier.height(12.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (state.skills.isEmpty()) {
                    Text("No skills added yet.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.outline)
                } else {
                    for (skill in state.skills) {
                        SuggestionChip(
                            onClick = { },
                            label = { Text(skill) },
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                }
            }
            
            Spacer(Modifier.height(40.dp))
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline)
            Text(
                if (value.isBlank()) "Not set" else value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.FlowRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement
    ) {
        content()
    }
}

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isPreview) {
        ProfilePreview(state = state, onBack = { viewModel.backToEdit() })
    } else {
        ProfileForm(
            state = state,
            onNameChange = { viewModel.onNameChange(it) },
            onEmailChange = { viewModel.onEmailChange(it) },
            onContactChange = { viewModel.onContactChange(it) },
            onAddressChange = { viewModel.onAddressChange(it) },
            onUsernameChange = { viewModel.onUsernameChange(it) },
            onNewSkillChange = { viewModel.onNewSkillChange(it) },
            addSkill = { viewModel.addSkill() },
            removeSkill = { viewModel.removeSkill(it) },
            showPreview = { viewModel.showPreview() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreviewModern() {
    MaterialTheme {
        ProfilePreview(
            state = ProfileUiState(
                name = "John Doe",
                email = "john.doe@example.com",
                contactNumber = "+1 234 567 890",
                address = "123 Modern St, Tech City",
                username = "johndoe_dev",
                skills = listOf("Kotlin", "Jetpack Compose", "Android", "Material Design")
            ),
            onBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileFormModern() {
    MaterialTheme {
        ProfileForm(
            state = ProfileUiState(
                name = "John Doe",
                skills = listOf("Kotlin", "Android")
            ),
            onNameChange = {},
            onEmailChange = {},
            onContactChange = {},
            onAddressChange = {},
            onUsernameChange = {},
            onNewSkillChange = {},
            addSkill = {},
            removeSkill = {},
            showPreview = {}
        )
    }
}
