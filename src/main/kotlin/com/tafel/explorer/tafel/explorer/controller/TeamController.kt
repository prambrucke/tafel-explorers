package com.tafel.explorer.tafel.explorer.controller

import com.tafel.explorer.tafel.explorer.model.ExtendedTeam
import com.tafel.explorer.tafel.explorer.model.Team
import com.tafel.explorer.tafel.explorer.service.TeamService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class TeamController(val teamService: TeamService) {

    @PostMapping("teams", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTeam(@RequestBody team: Team): ResponseEntity.HeadersBuilder<*> {
        return ResponseEntity.created(URI.create("/teams/"+teamService.createTeam(team)))
    }

    @GetMapping("teams/{team-id}")
    fun getTeamById(@PathVariable("team-id") teamId: String): ResponseEntity<Team> {
        return ResponseEntity.ok(teamService.getTeamById(teamId))
    }

    @GetMapping("teams/{team-id}/users")
    fun getExplorersByTeamId(@PathVariable("team-id") teamId: String): ResponseEntity<ExtendedTeam> {
        return ResponseEntity.ok(teamService.getExplorersByTeamId(teamId))
    }

    @PutMapping("teams")
    fun updateTeamById(@RequestBody team: Team): ResponseEntity.HeadersBuilder<*> {
        teamService.updateTeamById(team);
        return ResponseEntity.ok()
    }
}