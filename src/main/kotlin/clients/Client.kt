package clients

import kotlinx.serialization.Serializable

@Serializable
data class Client(val id: Int, val name: String, val address: String)
