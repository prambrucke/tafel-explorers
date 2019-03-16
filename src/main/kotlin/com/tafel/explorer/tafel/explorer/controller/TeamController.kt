package com.tafel.explorer.tafel.explorer.controller

import com.tafel.explorer.tafel.explorer.model.Team
import com.tafel.explorer.tafel.explorer.service.TeamService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TeamController(val teamService: TeamService) {

    @PostMapping("teams", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTeam(@RequestBody team: Team): ResponseEntity<Team> {
        System.out.println("------>"+team)
        return ResponseEntity.ok(teamService.createTeam(team))
    }

    @GetMapping("teams/{team-id}")
    fun getTeamById(@PathVariable("team-id") teamId: String): ResponseEntity<Team> {
        return ResponseEntity.ok(teamService.getTeamById(teamId))
    }

    @PutMapping("teams")
    fun updateTeamById(@RequestBody team: Team): Team {
        return teamService.updateTeamById(team);
    }
}