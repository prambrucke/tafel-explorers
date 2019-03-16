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
                "status" to ActivityStatus.ACTIVE.toString(),
                "created_by" to team.created_by,
                "created_at" to Date(),
                "updated_by" to team.updated_by,
                "updated_at" to Date()
                )
        namedParameterJdbcTemplate.update(SQLQueries.CREATE_TEAM, inputData)
        return team;
    }

    fun getTeamById(teamId: String): Team {
        val inputData = mapOf(
                "team_id" to teamId
        )
        val teams: List<Team> = namedParameterJdbcTemplate.query(SQLQueries.GET_TEAM_BY_ID, inputData )
                {rs, _ -> Team(rs.getInt("id"), rs.getString("name"), ActivityStatus.valueOf(rs.getString("status")),
                rs.getString("created_by"), rs.getDate("created_at"), rs.getString("updated_by"),
                rs.getDate("updated_at")) }
        return teams[0]
    }

    fun updateTeamById(team: Team): Team {
        val inputData = mapOf(
                "id" to team.id,
                "name" to team.name,
                "status" to team.status.toString(),
                "updated_by" to team.updated_by,
                "updated_at" to Date()
        )
        namedParameterJdbcTemplate.update(SQLQueries.UPDATE_TEAM_BY_ID, inputData)
        return team;
    }
}