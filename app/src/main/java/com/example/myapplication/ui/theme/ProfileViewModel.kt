package com.example.myapplication.ui.theme // Replace with your actual package name

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    // Private mutable state flow
    private val _uiState = MutableStateFlow(ProfileUiState())
    // Public read-only state flow exposed to the UI
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // --- Text Field Event Handlers ---
    fun onNameChange(value: String) =
        _uiState.update { it.copy(name = value) }

    fun onEmailChange(value: String) =
        _uiState.update { it.copy(email = value) }

    fun onContactChange(value: String) =
        _uiState.update { it.copy(contactNumber = value) }

    fun onAddressChange(value: String) =
        _uiState.update { it.copy(address = value) }

    fun onUsernameChange(value: String) =
        _uiState.update { it.copy(username = value) }

    fun onNewSkillChange(value: String) =
        _uiState.update { it.copy(newSkill = value) }

    // --- Skill List Operations ---
    fun addSkill() {
        val skill = _uiState.value.newSkill.trim()
        if (skill.isEmpty()) return
        _uiState.update { current ->
            current.copy(
                skills = current.skills + skill, // Immutable list concatenation
                newSkill = ""                     // Clear input box
            )
        }
    }

    fun removeSkill(skill: String) {
        _uiState.update { current ->
            current.copy(skills = current.skills - skill) // Immutable list subtraction
        }
    }

    // --- Navigation/Preview Toggles ---
    fun showPreview() = _uiState.update { it.copy(isPreview = true) }
    fun backToEdit() = _uiState.update { it.copy(isPreview = false) }
}