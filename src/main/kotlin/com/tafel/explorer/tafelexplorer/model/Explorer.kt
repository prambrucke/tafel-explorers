package com.tafel.explorer.tafelexplorer.model

import java.util.*

data class Explorer(
        var id: String?,
        var first_name: String?,
        var last_name: String?,
        var email: String?,
        var avatar: Objects,
        var teams: List<Team>?,
        var role: Role?
)