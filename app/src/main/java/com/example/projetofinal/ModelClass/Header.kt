package com.example.projetofinal

import com.example.projetofinal.Stats
import com.squareup.moshi.Json

data class Header(
    @Json(name="last_years") val last_years : Int,
    @Json(name="query") val query: String,
    @Json(name="stats") val stats: Stats
)