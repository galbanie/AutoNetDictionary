package com.github.galbanie.models

import com.github.galbanie.utils.ParameterType
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Created by Galbanie on 2017-08-20.
 */

object Sessions : Table("SESSIONS"){
    val id = integer("SESSION_ID").primaryKey().autoIncrement()
    val user_id = integer("USER_ID").references(Users.id,ReferenceOption.CASCADE)
    val isUserProcess = bool("IS_USER_PROCESS").default(false)
    val dateLogin = date("DATE_LOGIN")
    val dateLogout = date("DATE_LOGOUT")
}

object Users : Table("USERS"){
    val id = integer("USER_ID").primaryKey().autoIncrement()
    val username = varchar("USERNAME", 255).uniqueIndex()
    val password = varchar("PASSWORD",255)
    val passwordSalt = varchar("PASSWORD_SALT",255).nullable()
    val firstName = varchar("FIRST_NAME",255).nullable()
    val lastName = varchar("LAST_NAME",255).nullable()
    val email = varchar("EMAIL",255).nullable()
    val accountActive = bool("ACCOUNT_ACTIVE").default(false)
    val dateExpiration = date("DATE_EXPIRATION").nullable()
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}

object Roles : Table("ROLES"){
    val id = integer("ROLE_ID").primaryKey().autoIncrement()
    val roleName = varchar("ROLE_NAME", 255).uniqueIndex()
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}

object UsersRoles : Table("USERS_ROLES"){
    val user_id = integer("USER_ID").references(Users.id,ReferenceOption.CASCADE)
    val role_id = integer("ROLE_ID").references(Roles.id,ReferenceOption.CASCADE)
}

object Actions : Table("ACTIONS"){
    val id = integer("ACTION_ID").primaryKey().autoIncrement()
    val actionName = varchar("ACTION_NAME", 255).uniqueIndex()
}

object Parameters : Table("PARAMETERS"){
    val id = integer("PARAMETER_ID").primaryKey().autoIncrement()
    val type = enumeration("TYPE", ParameterType::class.java)
    val field = varchar("FIELD",255).nullable()
    val value = varchar("VALUE",255).nullable()
}

object Entries : Table("Entries"){
    val input_id = integer("INPUT").references(Parameters.id,ReferenceOption.CASCADE).primaryKey()
    val output_id = integer("OUTPUT").references(Parameters.id,ReferenceOption.CASCADE).primaryKey()
    val valid = bool("VALID").default(false)
    val creator_id = integer("USER_ID").references(Users.id,ReferenceOption.SET_NULL)
    val reporter_id = integer("USER_ID").references(Users.id,ReferenceOption.SET_NULL)
}

object ActionsParameters : Table("ACTIONS_PARAMETERS"){
    val param_id = integer("PARAMETER_ID").references(Parameters.id,ReferenceOption.CASCADE)
    val action_id = integer("ACTION_ID").references(Actions.id,ReferenceOption.CASCADE)
    val priority = integer("PRIORITY")
}