package com.github.galbanie.models.dsl.pcdb

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Created by Galbanie on 2017-09-30.
 */
/*object Alias : Table("alias") {
    val id = integer("AliasID").primaryKey()
    val name = varchar("AliasName",100)
}
*/


object Alias : IntIdTable("alias","ALiasID"){
    val name = varchar("AliasName",100)
}

class Alias_(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Alias_>(Alias)
    var name by Alias.name
}


/*object Categories : Table("categories") {
    val id = integer("CategoryID").primaryKey()
    val name = varchar("CategoryName",100)
}*/

object Categories : IntIdTable("categories","CategoryID"){
    val name = varchar("CategoryName",100)
}

class Category(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Category>(Categories)

    var name by Categories.name
}


/*object CodeMasters : Table("codeMaster") {
    val id = integer("CodeMasterID").primaryKey()
    val category_id = integer("CategoryID").references(Categories.id, ReferenceOption.CASCADE)
    val subCategory_id = integer("SubCategoryID").references(SubCategories.id, ReferenceOption.CASCADE)
    val partTerminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val position_id = integer("PositionID").references(Positions.id, ReferenceOption.CASCADE)
    val Revdate = datetime("RevisionDate")
}*/

object CodeMasters : IntIdTable("codeMatser","CodeMasterID"){
    val revdate = datetime("RevisionDate")
    val category_id = reference("CetegoriesID", Categories)
    val subCategory_id = reference("SubCategoryID", SubCategories)
    val partTerminology_id = reference("PartTerminolyID", Parts)
    val position_id = reference("PositionID", Positions)
}


class codeMaster(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<codeMaster>(CodeMasters)
    var revdate by CodeMasters.revdate
    var category  by Category referencedOn Categories.id
    var subcategory by Subcategory referencedOn SubCategories.id
    var partTerminology by Part referencedOn Parts.id
    var position by Position referencedOn Positions.id
}


/*object Parts : Table("parts") {
    val id = integer("PartTerminolyID").primaryKey()
    val name = varchar("PartTerminologyName", 100)
    val partdescription_id = integer("PartDescriptionID").references(PartDescriptions.id, ReferenceOption.CASCADE)
    val Revdate = datetime("RevDate")
}
*/

object Parts : IntIdTable("parts","PartTerminologyID"){
    val revdate = datetime("RevDate")
    val name = varchar("PartTerminologyName",100)
    val partdescription_id = reference("PartDescriptionID", PartDescriptions)

}

class Part(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Part>(Parts)
    var revdate by Parts.revdate
    var name by Parts.name
    var partdescription by Partdescription referencedOn PartDescriptions.id
}


/*object PartDescriptions : Table("partsdescription") {
    val id = integer("PartDescriptionID").primaryKey()
    val name = varchar("PartsDescription", 100)

}*/

object PartDescriptions : IntIdTable("partsdescription","PartDescriptionID"){
    val name = varchar("PartsDescription",100)

}

class Partdescription(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Partdescription>(PartDescriptions)
    var name by PartDescriptions.name
}


/*object PartRelationships : Table("partsrelationship") {
    val partterminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val relatedpartterminology_id = integer("RelatedPartTerminologyID").references(Parts.id, ReferenceOption.CASCADE)

}*/

// A voir, clés composées (To Do)

object PartRelationships : IntIdTable("parts","PartTerminologyID"){
    val revdate = datetime("RevDate")
    val name = varchar("PartTerminologyName",100)
    val partdescription_id = reference("PartDescriptionID", PartDescriptions)

}


class Partrelationship(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Partrelationship>(PartRelationships)

    var partterminology by Part referencedOn Parts.id
    var relatedpartterminology by Part referencedOn Parts.id
}

// A voir, clés composées (To Do)
/*object PartSupersessions : Table("partssupersession") {
    val oldpartterminology_id = integer("OldPartTerminologyID").references(Parts.id, ReferenceOption.CASCADE)
    val newpartterminology_id = integer("NewPartTerminologyID").references(Parts.id, ReferenceOption.CASCADE)
    val oldpartterminology_name = varchar("OldPartTerminologyName", 200).references(Parts.name, ReferenceOption.CASCADE)
    val newpartterminology_name = varchar("NewPartTerminologyName", 200).references(Parts.name, ReferenceOption.CASCADE)
    val revdate = datetime("RevDate")
}*/

// A voir, clés composées (To Do)

object PartSupersessions : IntIdTable("partssupersession","PartTerminologyID"){
    val revdate = datetime("RevDate")
    val oldpartterminology_id = reference("OldPartTerminologyID", Parts.id)
    val newpartterminology_id = reference("NewPartTerminologyID", Parts.id)
    val oldpartterminology_name = reference("OldPartTerminologyName", Parts.name)
    val newpartterminology_name = reference("NewPartTerminologyName", Parts.name)
}


class PartSupersession(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PartSupersession>(PartSupersessions)

    var oldpartterminology by Part referencedOn Parts.id
    var newpartterminology by Part referencedOn Parts.id

}


// A voir, clés composées (To Do)
/*object PartToAlias : Table("partstoalias") {
    val partterminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val alias_id = integer("AliasID").references(Alias.id, ReferenceOption.CASCADE)
}*/

// A voir, clés composées (To Do)
/*object PartToUse : Table("partstouse") {
    val partterminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val use_id = integer("UseID").references(Uses.id, ReferenceOption.CASCADE)
}*/



/*object Uses : Table ("use") {
    val id = integer("UseID").primaryKey()
    val name = varchar ("UseDescription", 100)
}*/


object Uses : IntIdTable("use","UseID"){
    val name = varchar("UseDescription",100)
}

class Use(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Use>(Uses)
    var name by Uses.name
}


/*object SubCategories : Table("subcategories") {
    val id = integer("SubCategoryID").primaryKey()
    val name = varchar ("SubCategoryName", 100)

}*/


object SubCategories : IntIdTable("subcategories","SubCategoryID"){
    val name = varchar("SubCategoryName",100)
}

class Subcategory(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Subcategory>(SubCategories)
    var name by SubCategories.name
}



/*object PCDBChanges : Table("pcdbchanges") {
    val codemaster_id = integer("CodeMasterID")
    val category_id = integer("CategoryID")
    val category_name = varchar ("CategoryName", 100)
    val subcategory_id = integer("SubCategoryID")
    val subcategory_name = varchar("SubCtageoryName", 100)
    val parterminology_id = integer("PartTerminologyID")
    val parterminology_name = varchar("PartTerminologyName", 100)
    val use_id = integer("UseID")
    val usedecription = varchar("UseDescription", 100)
    val position_id = integer("PositionID")
    val position_name = varchar("Position", 100)
    val revdate = datetime("RevDate")
    val action = varchar("Action", 20)
}*/

object PCDBChanges : IntIdTable("pcdbchanges"){
    val codemaster_id = integer("CodeMasterID")
    val category_id = integer("CategoryID")
    val category_name = varchar ("CategoryName", 100)
    val subcategory_id = integer("SubCategoryID")
    val subcategory_name = varchar("SubCtageoryName", 100)
    val parterminology_id = integer("PartTerminologyID")
    val parterminology_name = varchar("PartTerminologyName", 100)
    val use_id = integer("UseID")
    val usedecription = varchar("UseDescription", 100)
    val position_id = integer("PositionID")
    val position_name = varchar("Position", 100)
    val revdate = datetime("RevDate")
    val action = varchar("Action", 20)
}

class Pcbdchange(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Pcbdchange>(PCDBChanges)

    var codemaster_id by PCDBChanges.codemaster_id
    val category_id by PCDBChanges.category_id
    val category_name by PCDBChanges.category_name
    val subcategory_id by PCDBChanges.subcategory_id
    val subcategory_name by PCDBChanges.subcategory_name
    val parterminology_id by PCDBChanges.parterminology_id
    val parterminology_name by PCDBChanges.parterminology_name
    val use_id by PCDBChanges.use_id
    val usedecription by PCDBChanges.usedecription
    val position_id by PCDBChanges.position_id
    val position_name by PCDBChanges.position_name
    val revdate by PCDBChanges.revdate
    val action by PCDBChanges.action
}


/*object RetiredTerms : Table("retired terms") {
    val name = varchar ("PartName", 255)
    val part_id_code = integer("PartIDCode")
}*/

object RetireDTerms : IntIdTable("retired terms"){
    val name = varchar("PartName",255)
    val part_id_code = integer("PartIDCode")
}

class Retiredterm(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Retiredterm>(RetireDTerms)
    var name by RetireDTerms.name
    var part_id_code by RetireDTerms.part_id_code
}



/*object VersionsPCDB : Table("version") {
    val version_date = datetime("VersionDate")
}*/

object VersionsPCDB : IntIdTable("version"){
    val version_date = datetime("VersionDate")
}

class Versionpcdb(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Versionpcdb>(VersionsPCDB)
    var version_date by VersionsPCDB.version_date

}


/*object Positions : Table("positions") {
    val id = integer("PositionID").primaryKey()
    val position = varchar("Position",100)
}*/

object Positions : IntIdTable("positions","PositionID") {
    val position = varchar("Position",100)
}

class Position(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Position>(Positions)

    var position by Positions.position
}

fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://localhost:3306/pcdb", driver = "com.mysql.jdbc.Driver",user = "root",password = "")
    transaction {
        /*Positions.select { Positions.id eq 1 }.forEach {
            println("${it[Positions.id]} - ${it[Positions.position]}")
        }*/

        println("Position: ${Position.find { Positions.id eq  1 }.joinToString {it.position}}")
    }
}