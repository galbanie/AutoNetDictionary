package com.github.galbanie.models

import org.jetbrains.exposed.sql.Table

/**
 * Created by Galbanie on 2017-09-03.
 */

object Positions : Table("POSITIONS") {
    val id = integer("POSITION_ID").primaryKey()
    val position = varchar("POSITION",255)
}