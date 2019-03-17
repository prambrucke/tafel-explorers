package com.tafel.explorer.tafel.explorer.service

import com.tafel.explorer.tafel.explorer.dao.ExplorerDAO
import com.tafel.explorer.tafel.explorer.model.Explorer
import com.tafel.explorer.tafel.explorer.model.ExtendedExplorer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ExplorerService(val explorerDAO: ExplorerDAO) {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = [Exception::class])
    fun createExplorer(extendedExplorer: ExtendedExplorer): String {
        extendedExplorer.explorer.id = explorerDAO.createExplorer(extendedExplorer.explorer)
        explorerDAO.mapExplorerWithTeam(extendedExplorer.explorer, extendedExplorer.teams)
        return extendedExplorer.explorer.id.toString()
    }

    fun getExplorerById(explorerId: String): ExtendedExplorer {
        return ExtendedExplorer(explorerDAO.getExplorerById(explorerId), explorerDAO.getTeamsForExplorerById(explorerId))
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = [Exception::class])
    fun updateExplorerById(extendedExplorer: ExtendedExplorer): Unit {
        explorerDAO.updateExplorerById(extendedExplorer.explorer);
        explorerDAO.updateTeamForExplorerByExplorerId(extendedExplorer);
    }
}