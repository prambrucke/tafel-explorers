package com.tafel.explorer.tafel.explorer.constants

object SQLQueries {
    val CREATE_EXPLORER: String = """
        INSERT INTO "tafel-explorers".explorers(
                first_name, last_name, email, role, status, created_by, created_at, updated_by, updated_at)
        VALUES (:first_name, :last_name, :email, :role, :status,  :created_by, :created_at, :updated_by, :updated_at) RETURNING id;
    """.trimIndent()
    val MAP_EXPLORER_TO_TEAM: String = """
        INSERT INTO "tafel-explorers".explorer_team(
                team_id, explorer_id, status, created_by, created_at, updated_by, updated_at)
        VALUES (:team_id, :explorer_id, :status, :created_by, :created_at, :updated_by, :updated_at);
    """.trimIndent()
    val CREATE_TEAM: String = """
        INSERT INTO "tafel-explorers".teams(
                name, status, created_by, created_at, updated_by, updated_at)
        VALUES (:name, :status, :created_by, :created_at, :updated_by, :updated_at);
    """.trimIndent()
    val GET_EXPLORER_BY_ID: String = """
        SELECT explorer.id, explorer.first_name, explorer.last_name, explorer.email, explorer.role, explorer.status,
        explorer.created_by, explorer.updated_by, explorer.updated_at, teams.id, teams.name from
            "tafel-explorers".explorers explorer, "tafel-explorers".teams teams, "tafel-explorers".explorer_team mapper
        WHERE mapper.explorer_id = :explorer_id and mapper.explorer_id = explorer.id and teams.id = mapper.team_id
    """.trimIndent()
}