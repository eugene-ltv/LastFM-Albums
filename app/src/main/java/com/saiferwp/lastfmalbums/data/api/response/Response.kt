package com.saiferwp.lastfmalbums.data.api.response

import com.google.gson.annotations.SerializedName

abstract class Response {
    @SerializedName("message")
    val message: String? = null
    @SerializedName("error")
    val error: Int? = null
}