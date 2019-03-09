package com.tafel.explorer.tafel.explorer.dao

import com.tafel.explorer.tafel.explorer.model.Explorer
import com.tafel.explorer.tafel.explorer.model.Role
import com.tafel.explorer.tafel.explorer.model.Team
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ExplorerDAO(val jdbcTemplate: JdbcTemplate) {
    fun createExplorer(explorer: Explorer): Explorer {
        System.out.println(explorer)
        return explorer;
    }

    fun getExplorerById(explorerId: String): Explorer {
        val lst: List<Team> = emptyList();
        return Explorer("1", "Explorer1-FirstName", "Explorer1-LastName", "Explorer1@mail.com", lst, Role.USER);
    }

    fun updateExplorerById(explorerId: String, explorer: Explorer): Explorer {
        return explorer;
    }
}