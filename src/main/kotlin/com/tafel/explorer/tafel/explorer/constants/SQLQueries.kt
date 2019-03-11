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
    val GET_EXPLORER_DETAILS_BY_ID: String = """
        SELECT id, first_name, last_name, email, role, status, created_by, created_at, updated_by, updated_at from
            "tafel-explorers".explorers  WHERE id::varchar = :explorer_id
    """.trimIndent()

    val GET_EXPLORER_TEAM_DETAILS_BY_ID: String = """
        SELECT teams.id, teams.name, teams.status, teams.created_by, teams.created_at, teams.updated_by, teams.updated_at from
        "tafel-explorers".teams teams, "tafel-explorers".explorer_team mapper
        where mapper.explorer_id::varchar = :explorer_id and mapper.team_id = teams.id
    """.trimIndent()
}