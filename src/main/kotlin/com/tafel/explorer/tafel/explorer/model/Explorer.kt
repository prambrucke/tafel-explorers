package com.tafel.explorer.tafel.explorer.model

data class Explorer(
        val id: String?,
        val first_name: String?,
        val last_name: String?,
        val email: String?,
        /*val avatar: Objects,*/
        val teams: List<Team>?,
        val role: Role?
)