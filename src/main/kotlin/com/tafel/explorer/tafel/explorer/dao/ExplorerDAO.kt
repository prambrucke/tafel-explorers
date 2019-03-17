package com.tafel.explorer.tafel.explorer.dao

import com.tafel.explorer.tafel.explorer.constants.SQLQueries
import com.tafel.explorer.tafel.explorer.model.*
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList


@Repository
class ExplorerDAO(val namedJdbcTemplate: NamedParameterJdbcTemplate) {

    fun createExplorer(explorer: Explorer): Int {
        val keyHolder: KeyHolder = GeneratedKeyHolder();
        val inputData = MapSqlParameterSource()
        inputData.addValue("first_name", explorer.first_name)
        inputData.addValue("last_name", explorer.last_name)
        inputData.addValue("email", explorer.email)
        inputData.addValue("role", explorer.role.toString())
        inputData.addValue("status", ActivityStatus.ACTIVE.toString())
        inputData.addValue("created_by", explorer.created_by)
        inputData.addValue("created_at", Date())
        inputData.addValue("updated_by", explorer.updated_by)
        inputData.addValue("updated_at", Date())
        namedJdbcTemplate.update(SQLQueries.CREATE_EXPLORER, inputData, keyHolder)
        return keyHolder.key!!.toInt()
    }

    fun mapExplorerWithTeam(explorer: Explorer, teams: Collection<Team>): Boolean{
        teams.forEach {
            val inputData = mapOf("team_id" to it.id,
                    "explorer_id" to explorer.id,
                    "status" to ActivityStatus.ACTIVE.toString(),
                    "created_at" to Date(),
                    "created_by" to explorer.created_by,
                    "updated_at" to Date(),
                    "updated_by" to explorer.updated_by
            )
            namedJdbcTemplate.update(SQLQueries.MAP_EXPLORER_TO_TEAM, inputData)
        }
        return true
    }

    fun getExplorerById(explorerId: String): Explorer {
        val inputData = mapOf("explorer_id" to explorerId)
        val explorers: List<Explorer> = namedJdbcTemplate.query(SQLQueries.GET_EXPLORER_DETAILS_BY_ID, inputData){
            rs: ResultSet, _: Int ->
            Explorer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("email"), Role.valueOf(rs.getString("role")),
                    ActivityStatus.valueOf(rs.getString("status")), rs.getString("created_by"),
                    rs.getDate("created_at"), rs.getString("updated_by"), rs.getDate("updated_at"))
        }
        return explorers[0]
    }

    fun getTeamsForExplorerById(explorerId: String): List<Team>{
        val inputData = mapOf("explorer_id" to explorerId)
        return namedJdbcTemplate.query(SQLQueries.GET_EXPLORER_TEAM_DETAILS_BY_ID, inputData){rs: ResultSet, _->
            Team(rs.getInt("id"), rs.getString("name"), ActivityStatus.valueOf(rs.getString("status")),
                    rs.getString("created_by"), rs.getDate("created_at"), rs.getString("updated_by"),
                    rs.getDate("updated_at"))}
    }

    fun updateExplorerById(explorer: Explorer): Boolean {
        val inputData = MapSqlParameterSource()
        inputData.addValue("explorer_id", explorer.id)
        inputData.addValue("first_name", explorer.first_name)
        inputData.addValue("last_name", explorer.last_name)
        inputData.addValue("email", explorer.email)
        inputData.addValue("role", explorer.role.toString())
        inputData.addValue("status", explorer.status.toString())
        inputData.addValue("updated_by", explorer.updated_by)
        inputData.addValue("updated_at", Date())
        namedJdbcTemplate.update(SQLQueries.UPDATE_EXPLORER_DETAILS_BY_ID, inputData)
        return true
    }

    fun updateTeamForExplorerByExplorerId(extendedExplorer: ExtendedExplorer): Boolean{
        val teamsInDB:MutableSet<String> = getTeamsForExplorerById(extendedExplorer.explorer.id.toString())
                                                .stream()
                                                .map { it.id.toString() }.collect(Collectors.toSet())
        val newTeams: MutableList<Team> = ArrayList()
        extendedExplorer.teams.forEach(){
            /* If teams from front end is available in the DB, then remove it from teamsInDB set, if not available then it is a new team
            * teams that are leftoff in the teamsInDB after comparing teamsCurrent is the teams that got removed from the explorer*/
            if(teamsInDB.contains(it.id.toString())){
                teamsInDB.remove(it.id.toString())
            }
            else{
                newTeams.add(it)
            }
        }
        removeTeamFromExplorer(extendedExplorer.explorer, teamsInDB)
        mapExplorerWithTeam(extendedExplorer.explorer, newTeams)
        return true
    }

    private fun removeTeamFromExplorer(explorer: Explorer, teams: Collection<String>){
        teams.forEach(){ it ->
                val inputData = mapOf("team_id" to it,
                                    "explorer_id" to explorer.id,
                                    "status" to ActivityStatus.INACTIVE.toString(),
                                    "updated_by" to explorer.updated_by,
                                    "updated_at" to explorer.updated_at)
            namedJdbcTemplate.update(SQLQueries.REMOVE_TEAM_MAPPING_FOR_EXPLORER_BY_ID, inputData)
        }
    }
}