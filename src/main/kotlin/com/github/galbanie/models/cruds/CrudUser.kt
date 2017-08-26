package com.github.galbanie.models.cruds

import com.github.galbanie.models.User
import org.jetbrains.exposed.sql.Column

/**
 * Created by Galbanie on 2017-08-25.
 */
class CrudUser : CrudTable<User> {
    override fun createTable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(t: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): Iterable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(t: User): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findBy(column: Column<*>, value: Any): Iterable<User>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(t: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findColumnName(): Iterable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}