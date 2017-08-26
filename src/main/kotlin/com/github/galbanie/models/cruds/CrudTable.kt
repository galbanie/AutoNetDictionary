package com.github.galbanie.models.cruds

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * Created by Galbanie on 2017-08-25.
 */
interface CrudTable<T> {
    fun createTable()
    fun create(t : T) : T
    fun findAll() : Iterable<T>
    fun deleteAll() : Int
    fun delete(t : T) : Int
    fun findBy(column : Column<*>, value : Any) : Iterable<T>?
    fun update(t : T)
    fun findColumnName() : Iterable<String>
}