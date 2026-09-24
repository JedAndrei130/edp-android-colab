package com.liceo.account.domain.model

data class User(
    val id: String,
    val fullName: String,
    val email: String,
    val birthdate: String
)