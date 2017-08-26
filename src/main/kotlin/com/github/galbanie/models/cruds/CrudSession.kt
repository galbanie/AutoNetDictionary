package com.github.galbanie.models.cruds

import com.github.galbanie.models.Session
import com.github.galbanie.models.Sessions
import com.github.galbanie.models.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * Created by Galbanie on 2017-08-25.
 */
class CrudSession : CrudTable<Session> {
    fun toRow(t: Session): Sessions.(UpdateBuilder<*>) -> Unit = {
        it[id] = t.id
        it[user_id] = t.user.id
        it[isUserProcess] = t.isUserProcess
        /*it[dateLogin] = t.dateLogin
        it[dateLogout] = t.dateLogout*/
    }

    fun fromRow(r: ResultRow) = Session().apply{
        id = r[Sessions.id]
        user = CrudUser().findBy(Users.id,r[Sessions.id])?.first()
        isUserProcess = r[Sessions.isUserProcess]
        /*dateLogin = r[Sessions.dateLogin]
        dateLogout = r[Sessions.dateLogout]*/
    }

    override fun createTable() {
        SchemaUtils.create(Sessions)
    }

    override fun create(t: Session): Session {
        t.id = Sessions.insert(toRow(t))[Sessions.id]
        return t
    }

    override fun findAll(): Iterable<Session> = Sessions.selectAll().map { fromRow(it) }

    override fun deleteAll(): Int  = Sessions.deleteAll()

    override fun delete(t: Session): Int = Sessions.deleteWhere { Sessions.id eq t.id }

    override fun findBy(column: Column<*>, value: Any): Iterable<Session>? = Sessions.select{column eq value}.map { fromRow(it) }

    override fun update(t: Session) {
        Sessions.update({Sessions.id eq t.id}){
            it[user_id] = t.user.id
            it[isUserProcess] = t.isUserProcess
            /*it[dateLogin] = t.dateLogin
            it[dateLogout] = t.dateLogout*/
        }
    }

    override fun findColumnName(): Iterable<String> = Sessions.columns.map { it.name }
}