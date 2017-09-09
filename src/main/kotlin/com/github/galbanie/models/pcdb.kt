package com.github.galbanie.models

import org.jetbrains.exposed.sql.Table

/**
 * Created by Galbanie on 2017-09-03.
 */

object Alias : Table("alias") {
    val id = integer("AliasID").primaryKey()
    val name = varchar("AliasName",100)
}

object Categories : Table("categories") {
    val id = integer("CategoryID").primaryKey()
    val name = varchar("CategoryName",100)
}

object CodeMaster : Table("codemaster") {
    val id = integer("CodeMasterID").primaryKey()
    val category_id = integer("CategoryID").references(Categories.id)
    //val subCategory_id = integer("SubCategoryID").references(SubCategories.id)

}

object Positions : Table("positions") {
    val id = integer("PositionID").primaryKey()
    val position = varchar("Position",100)
}