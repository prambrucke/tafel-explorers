package com.tafel.explorer.tafel.explorer.dao

import com.tafel.explorer.tafel.explorer.model.Team
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class TeamDAO(val jdbcTemplate: JdbcTemplate) {

    fun createTeam(team: Team): Team {
        System.out.println(team)
        return team;
    }

    fun getTeamById(teamId: String): Team {
        return Team("1", "TEGEL")
    }

    fun updateTeamById(teamId: String, team: Team): Team {
        return team;
    }
}