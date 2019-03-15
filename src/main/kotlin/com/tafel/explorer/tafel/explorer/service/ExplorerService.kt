package com.tafel.explorer.tafel.explorer.service

import com.tafel.explorer.tafel.explorer.dao.ExplorerDAO
import com.tafel.explorer.tafel.explorer.model.Explorer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ExplorerService(val explorerDAO: ExplorerDAO) {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = [Exception::class])
    fun createExplorer(explorer: Explorer): Explorer {
        return explorerDAO.createExplorer(explorer)
    }

    fun getExplorerById(explorerId: String): Explorer {
        return explorerDAO.getExplorerById(explorerId)
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = [Exception::class])
    fun updateExplorerById(explorer: Explorer): Explorer {
        return explorerDAO.updateExplorerById(explorer);
    }
}