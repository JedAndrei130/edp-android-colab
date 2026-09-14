package com.example.myapplication.domain

import com.example.myapplication.core.AppResult

data class Message(
    val id: String,
    val sender: String,
    val text: String,
    val createdAt: Long
)

interface ChatRepository {
    suspend fun getMessages(): AppResult<List<Message>>
    suspend fun sendMessage(sender: String, text: String): AppResult<Unit>
}
