package com.tafel.explorer.tafel.explorer.service

import com.tafel.explorer.tafel.explorer.dao.TeamDAO
import com.tafel.explorer.tafel.explorer.model.Team
import org.springframework.stereotype.Service

@Service
class TeamService(val teamDAO: TeamDAO) {

    fun createTeam(team: Team): Team {
        return teamDAO.createTeam(team)
    }

    fun getTeamById(teamId: String): Team {
        return teamDAO.getTeamById(teamId)
    }

    fun updateTeamById(teamId: String, team: Team): Team {
        return teamDAO.updateTeamById(teamId, team)
    }
}