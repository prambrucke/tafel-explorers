package com.tafel.explorer.tafel.explorer.service

import com.tafel.explorer.tafel.explorer.dao.TeamDAO
import com.tafel.explorer.tafel.explorer.model.Explorer
import com.tafel.explorer.tafel.explorer.model.ExtendedTeam
import com.tafel.explorer.tafel.explorer.model.Team
import org.springframework.stereotype.Service

@Service
class TeamService(val teamDAO: TeamDAO) {

    fun createTeam(team: Team): String {
        return teamDAO.createTeam(team)
    }

    fun getTeamById(teamId: String): Team {
        return teamDAO.getTeamById(teamId)
    }

    fun getExplorersByTeamId(teamId: String): ExtendedTeam {
        return teamDAO.getExplorersByTeamId(teamId)
    }

    fun updateTeamById(team: Team): Unit {
        teamDAO.updateTeamById(team)
    }
}