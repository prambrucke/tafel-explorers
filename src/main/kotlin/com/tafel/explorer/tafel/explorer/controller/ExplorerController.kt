package com.tafel.explorer.tafel.explorer.controller

import com.tafel.explorer.tafel.explorer.model.Explorer
import com.tafel.explorer.tafel.explorer.service.ExplorerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ExplorerController(var explorerService: ExplorerService) {

    @PostMapping("explorers")
    fun createExplorer(explorer: Explorer): ResponseEntity<Explorer>{
        System.out.println("------>"+explorer)
        return ResponseEntity.ok(explorerService.createExplorer(explorer))
    }

    @GetMapping("explorers/{explorer-id}")
    fun getExplorerById(@PathVariable("explorer-id") explorerId: String): ResponseEntity<Explorer>{
        return ResponseEntity.ok(explorerService.getExplorerById(explorerId))
    }

    @PutMapping("explorers/{explorer-id}")
    fun updateExplorerById(@PathVariable("explorer-id") explorerId: String, explorer: Explorer): Explorer {
        return explorerService.updateExplorerById(explorerId, explorer);
    }
}