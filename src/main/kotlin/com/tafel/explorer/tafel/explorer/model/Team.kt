package com.tafel.explorer.tafel.explorer.model

import java.util.*

data class Team (
        val id: Int?,
        val name: String?,
        val status: ActivityStatus?,
        val created_by: String?,
        val created_at: Date?,
        val updated_by: String?,
        val updated_at: Date ?
)