package com.liceo.account.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String? = null,
    val fullname: String? = null,
    val email: String? = null,
    val password: String? = null,
    val birthdate: String? = null
)

@Serializable
data class NewUserDto(
    val fullname: String,
    val email: String,
    val password: String,
    val birthdate: String
)