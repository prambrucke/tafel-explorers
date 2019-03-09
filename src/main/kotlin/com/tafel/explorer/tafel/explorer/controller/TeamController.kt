package com.tafel.explorer.tafel.explorer.controller

import com.tafel.explorer.tafel.explorer.model.Team
import com.tafel.explorer.tafel.explorer.service.TeamService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TeamController(val teamService: TeamService) {

    @PostMapping("teams")
    fun createTeam(team: Team): ResponseEntity<Team> {
        System.out.println("------>"+team)
        return ResponseEntity.ok(teamService.createTeam(team))
    }

    @GetMapping("teams/{team-id}")
    fun getTeamById(@PathVariable("team-id") teamId: String): ResponseEntity<Team> {
        return ResponseEntity.ok(teamService.getTeamById(teamId))
    }

    @PutMapping("teams/{team-id}")
    fun updateTeamById(@PathVariable("team-id") teamId: String, team: Team): Team {
        return teamService.updateTeamById(teamId, team);
    }
}