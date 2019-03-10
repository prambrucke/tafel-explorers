package com.tafel.explorer.tafel.explorer.model

import java.util.*

data class Explorer(
        var id: Int?,
        val first_name: String,
        val last_name: String,
        val email: String,
        var teams: List<Team>?,
        val role: Role?,
        val status: ActivityStatus?,
        val created_by: String?,
        val created_at: Date?,
        val updated_by: String,
        val updated_at: Date?
)