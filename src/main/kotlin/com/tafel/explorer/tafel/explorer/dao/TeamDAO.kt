package com.tafel.explorer.tafel.explorer.dao

import com.tafel.explorer.tafel.explorer.constants.SQLQueries
import com.tafel.explorer.tafel.explorer.model.*
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TeamDAO(val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) {

    fun createTeam(team: Team): String {
        val keyHolder: KeyHolder = GeneratedKeyHolder();
        val inputData = MapSqlParameterSource()
        inputData.addValue("name" , team.name)
        inputData.addValue("status" , ActivityStatus.ACTIVE.toString())
        inputData.addValue("created_by" , team.created_by)
        inputData.addValue("created_at" , Date())
        inputData.addValue("updated_by" , team.updated_by)
        inputData.addValue("updated_at" , Date())

        namedParameterJdbcTemplate.update(SQLQueries.CREATE_TEAM, inputData, keyHolder)
        return keyHolder.key!!.toString();
    }

    fun getTeamById(teamId: String): Team {
        val inputData = mapOf("team_id" to teamId)
        val teams: List<Team> = namedParameterJdbcTemplate.query(SQLQueries.GET_TEAM_BY_ID, inputData )
                {rs, _ -> Team(rs.getInt("id"), rs.getString("name"), ActivityStatus.valueOf(rs.getString("status")),
                rs.getString("created_by"), rs.getDate("created_at"), rs.getString("updated_by"),
                rs.getDate("updated_at")) }
        return teams[0]
    }

    fun getExplorersByTeamId(teamId: String): ExtendedTeam {
        val inputData = mapOf("team_id" to teamId)
        val team = Team(teamId.toInt())
        return ExtendedTeam(team, namedParameterJdbcTemplate.query(SQLQueries.GET_EXPLORERS_FOR_TEAM_ID, inputData)
            {rs, _ -> Explorer( rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("email"), Role.valueOf(rs.getString("role")),
                    ActivityStatus.valueOf(rs.getString("status")))
            })
    }

    fun updateTeamById(team: Team): Unit {
        val inputData = mapOf(
                "id" to team.id,
                "name" to team.name,
                "status" to team.status.toString(),
                "updated_by" to team.updated_by,
                "updated_at" to Date()
        )
        namedParameterJdbcTemplate.update(SQLQueries.UPDATE_TEAM_BY_ID, inputData)
    }
}