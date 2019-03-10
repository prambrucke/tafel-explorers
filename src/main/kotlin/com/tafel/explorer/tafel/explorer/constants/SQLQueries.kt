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
                id, name, status, created_by, created_at, updated_by, updated_at)
        VALUES (:id, :name, :status, :created_by, :created_at, :updated_by, :updated_at) RETURNING id;
    """.trimIndent()
}