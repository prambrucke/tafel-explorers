package com.tafel.explorer.tafel.explorer.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tafel.explorer.tafel.explorer.model.Explorer
import com.tafel.explorer.tafel.explorer.service.ExplorerService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ExplorerController(var explorerService: ExplorerService, val objectMapper: ObjectMapper) {

    @PostMapping("explorers", consumes = [MediaType.APPLICATION_JSON_VALUE] , produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createExplorer(@RequestBody explorer: Explorer): ResponseEntity<Explorer>{
        System.out.println("------>"+explorer)
        return ResponseEntity.ok(explorerService.createExplorer(explorer))
    }

    @GetMapping("explorers/{explorer-id}")
    fun getExplorerById(@PathVariable("explorer-id") explorerId: String): ResponseEntity<Explorer>{
        return ResponseEntity.ok(explorerService.getExplorerById(explorerId))
    }

    @PutMapping("explorers/{explorer-id}")
    fun updateExplorerById(@PathVariable("explorer-id") explorerId: String, @RequestBody explorer: Explorer): Explorer {
        return explorerService.updateExplorerById(explorer);
    }
}