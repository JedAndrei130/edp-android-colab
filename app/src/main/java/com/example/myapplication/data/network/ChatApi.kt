package com.example.myapplication.data.network

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

object FlexibleLongSerializer : KSerializer<Long> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("FlexibleLong", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: Long) = encoder.encodeLong(value)
    override fun deserialize(decoder: Decoder): Long {
        return if (decoder is JsonDecoder) {
            val element = decoder.decodeJsonElement() as JsonPrimitive
            element.longOrNull ?: element.content.toLongOrNull() ?: 0L
        } else {
            decoder.decodeLong()
        }
    }
}

@Serializable
data class MessageDto(
    val id: String? = null,
    val sender: String? = null,
    val text: String? = null,
    @Serializable(with = FlexibleLongSerializer::class)
    val createdAt: Long? = null
)

@Serializable
data class NewMessageDto(
    val sender: String,
    val text: String,
    @Serializable(with = FlexibleLongSerializer::class)
    val createdAt: Long
)

interface ChatApiService {
    @GET("messages")
    suspend fun getMessages(
        @Query("sortBy") sortBy: String = "createdAt",
        @Query("order") order: String = "desc"
    ): List<MessageDto>

    @POST("messages")
    suspend fun sendMessage(
        @Body message: NewMessageDto
    ): MessageDto
}
