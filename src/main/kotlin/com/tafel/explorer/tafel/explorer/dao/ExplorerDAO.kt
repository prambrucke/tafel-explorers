package com.tafel.explorer.tafel.explorer.dao

import com.tafel.explorer.tafel.explorer.constants.SQLQueries
import com.tafel.explorer.tafel.explorer.model.ActivityStatus
import com.tafel.explorer.tafel.explorer.model.Explorer
import com.tafel.explorer.tafel.explorer.model.Role
import com.tafel.explorer.tafel.explorer.model.Team
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*


@Repository
class ExplorerDAO(val namedJdbcTemplate: NamedParameterJdbcTemplate) {

    fun createExplorer(explorer: Explorer): Explorer {
        val keyHolder: KeyHolder = GeneratedKeyHolder();
        return createExplorerInDatabase(explorer, keyHolder)
    }

    private fun createExplorerInDatabase(explorer: Explorer, keyHolder: KeyHolder): Explorer{
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
        explorer.id = keyHolder.key!!.toInt()
        associateExplorerWithTeam(explorer)
        return explorer
    }

    private fun associateExplorerWithTeam(explorer: Explorer): Boolean{
        val teams: List<Team>? = explorer.teams
        if (null == teams || teams.isEmpty())
            return true
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
        val explorers: List<Explorer> = namedJdbcTemplate.query(SQLQueries.GET_EXPLORER_DETAILS_BY_ID, inputData, RowMapper<Explorer>{
            rs: ResultSet, rowNum: Int ->
            Explorer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("email"), emptyList(), Role.valueOf(rs.getString("role")),
                    ActivityStatus.valueOf(rs.getString("status")), rs.getString("created_by"),
                    rs.getDate("created_at"), rs.getString("updated_by"), rs.getDate("updated_at"))
        })
        var explorer = explorers.get(0);
        explorer.teams = getTeamsForExplorerById(explorerId)
        return explorer
    }

    fun getTeamsForExplorerById(explorerId: String): List<Team>{
        val inputData = mapOf("explorer_id" to explorerId)
        val teams: List<Team> = namedJdbcTemplate.query(SQLQueries.GET_EXPLORER_TEAM_DETAILS_BY_ID, inputData, RowMapper<Team>{
            rs: ResultSet, i: Int ->
            Team(rs.getInt("id"), rs.getString("name"), ActivityStatus.valueOf(rs.getString("status")),
                    rs.getString("created_by"), rs.getDate("created_at"), rs.getString("updated_by"),
                    rs.getDate("updated_at"))
        })
        return teams;
    }

    fun updateExplorerById(explorerId: String, explorer: Explorer): Explorer {
        return explorer;
    }
}