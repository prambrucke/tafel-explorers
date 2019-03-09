package com.tafel.explorer.tafel.explorer.service

import com.tafel.explorer.tafel.explorer.dao.ExplorerDAO
import com.tafel.explorer.tafel.explorer.model.Explorer
import org.springframework.stereotype.Service

@Service
class ExplorerService(val explorerDAO: ExplorerDAO) {

    fun createExplorer(explorer: Explorer): Explorer {
        return explorerDAO.createExplorer(explorer)
    }

    fun getExplorerById(explorerId: String): Explorer {
        return explorerDAO.getExplorerById(explorerId)
    }

    fun updateExplorerById(explorerId: String, explorer: Explorer): Explorer {
        return explorerDAO.updateExplorerById(explorerId, explorer);
    }
}