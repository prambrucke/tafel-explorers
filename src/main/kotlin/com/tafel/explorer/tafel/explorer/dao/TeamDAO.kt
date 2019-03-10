package com.tafel.explorer.tafel.explorer.dao

import com.tafel.explorer.tafel.explorer.constants.SQLQueries
import com.tafel.explorer.tafel.explorer.model.ActivityStatus
import com.tafel.explorer.tafel.explorer.model.Team
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TeamDAO(val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) {

    fun createTeam(team: Team): Team {
        val inputData = mapOf(
                "name" to team.name,
                "status" to ActivityStatus.ACTIVE,
                "created_by" to team.created_by,
                "created_at" to Date(),
                "updated_by" to team.updated_by,
                "updated_at" to Date()
                )
        namedParameterJdbcTemplate.update(SQLQueries.CREATE_TEAM, inputData)
        return team;
    }

    fun getTeamById(teamId: String): Team {
        return Team(1, "TEGEL", null, null, null,"", Date())
    }

    fun updateTeamById(teamId: String, team: Team): Team {
        return team;
    }
}