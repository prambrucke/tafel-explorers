package com.tafel.explorer.tafel.explorer.model

import java.util.*

data class Explorer(
        var id: Int?,
        val first_name: String?,
        val last_name: String?,
        val email: String?,
        val role: Role?,
        val status: ActivityStatus?,
        val created_by: String?,
        val created_at: Date?,
        val updated_by: String?,
        val updated_at: Date?
){
    constructor( id: Int?,
                first_name: String?,
                last_name: String?,
                email: String?,
                role: Role?,
                status: ActivityStatus?) :
            this(id, first_name, last_name, email, role, status, null, null, null, null)
}

data class ExtendedExplorer (val explorer: Explorer, val teams: Collection<Team>)