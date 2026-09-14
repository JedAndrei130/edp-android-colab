package com.example.myapplication.data.repository

import com.example.myapplication.core.AppResult
import com.example.myapplication.data.network.ChatApiService
import com.example.myapplication.data.network.MessageDto
import com.example.myapplication.data.network.NewMessageDto
import com.example.myapplication.domain.ChatRepository
import com.example.myapplication.domain.Message
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun MessageDto.toDomain(): Message = Message(
    id = id ?: "",
    sender = sender ?: "Unknown",
    text = text ?: "",
    createdAt = createdAt ?: 0L
)

fun List<MessageDto>.toDomain(): List<Message> = map { it.toDomain() }

class ChatRepositoryImpl(
    private val api: ChatApiService
) : ChatRepository {

    override suspend fun getMessages(): AppResult<List<Message>> =
        safeCall { api.getMessages().toDomain() }

    override suspend fun sendMessage(sender: String, text: String): AppResult<Unit> =
        safeCall {
            val dto = NewMessageDto(sender, text, System.currentTimeMillis())
            api.sendMessage(dto)
            Unit
        }

    private inline fun <T> safeCall(block: () -> T): AppResult<T> =
        try { AppResult.Success(block()) }
        catch (e: UnknownHostException)   { AppResult.Failure.NoInternet }
        catch (e: SocketTimeoutException) { AppResult.Failure.Timeout }
        catch (e: IOException)            { AppResult.Failure.NoInternet }
        catch (e: retrofit2.HttpException) { 
            android.util.Log.e("ChatRepo", "HTTP Error", e)
            AppResult.Failure.Unknown("API Error: ${e.code()} ${e.message()}") 
        }
        catch (e: Exception) { 
            android.util.Log.e("ChatRepo", "SafeCall Error", e)
            AppResult.Failure.Unknown(e.message ?: e.toString()) 
        }
}
