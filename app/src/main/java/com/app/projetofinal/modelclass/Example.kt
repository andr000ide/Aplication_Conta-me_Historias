package com.app.projetofinal

import com.squareup.moshi.Json

data class Example (
    @Json(name="header") val header: Header,
    @Json(name="result") val result: Result,
    @Json(name="message") val message: String
)