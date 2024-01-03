package com.example.pokeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.model.Type.Type


@Entity
class DbType (
    @PrimaryKey
    val type: String,
    )

    fun Type.asDbType() : DbType = DbType  (
        type = type
    )

    fun DbType.asDomainType() : Type = Type(
        type = type
    )

    fun List<DbType>.asDomainTypes() = map { it.asDomainType() }