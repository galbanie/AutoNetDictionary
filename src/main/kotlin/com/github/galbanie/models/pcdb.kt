package com.github.galbanie.models

import com.github.galbanie.models.PartRelationships.references
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import tornadofx.*

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

object CodeMasters : Table("codeMaster") {
    val id = integer("CodeMasterID").primaryKey()
    val category_id = integer("CategoryID").references(Categories.id, ReferenceOption.CASCADE)
    val subcategory_id = integer("SubCategoryID").references(SubCategories.id, ReferenceOption.CASCADE)
    val partterminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val position_id = integer("PositionID").references(Positions.id, ReferenceOption.CASCADE)
    val Revdate = datetime("RevisionDate")
}


object Parts : Table("parts") {
    val id = integer("PartTerminolyID").primaryKey()
    val name = varchar("PartTerminologyName", 100)
    val partdescription_id = integer("PartDescriptionID").references(PartDescriptions.id, ReferenceOption.CASCADE)
    val Revdate = datetime("RevDate")
}

object PartDescriptions : Table("partsdescription") {
    val id = integer("PartDescriptionID").primaryKey()
    val name = varchar("PartsDescription", 100)

}

object PartRelationships : Table("partsrelationship") {
    val partterminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val relatedpartterminology_id = integer("RelatedPartTerminologyID").references(Parts.id, ReferenceOption.CASCADE)

}

object PartSupersessions : Table("partssupersession") {
    val oldpartterminology_id = integer("OldPartTerminologyID").references(Parts.id, ReferenceOption.CASCADE)
    val newpartterminology_id = integer("NewPartTerminologyID").references(Parts.id, ReferenceOption.CASCADE)
    val oldpartterminology_name = varchar("OldPartTerminologyName", 200).references(Parts.name, ReferenceOption.CASCADE)
    val newpartterminology_name = varchar("NewPartTerminologyName", 200).references(Parts.name, ReferenceOption.CASCADE)
    val Revdate = CodeMasters.datetime("RevDate")
}

object PartToAlias : Table("partstoalias") {
    val partterminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val alias_id = integer("AliasID").references(Alias.id, ReferenceOption.CASCADE)
}


object PartToUse : Table("partstouse") {
    val partterminology_id = integer("PartTerminolyID").references(Parts.id, ReferenceOption.CASCADE)
    val use_id = integer("UseID").references(Uses.id, ReferenceOption.CASCADE)
}

object Uses : Table ("use") {
    val id = integer("UseID").primaryKey()
    val name = varchar ("UseDescription", 100)
}


object SubCategories : Table("subcategories") {
    val id = integer("SubCategoryID").primaryKey()
    val name = varchar ("SubCategoryName", 100)

}

object PCDBChanges : Table("pcdbchanges") {
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


object RetiredTerms : Table("retired terms") {
    val name = varchar ("PartName", 255)
    val part_id_Code = integer("PartIDCode")
}

object VersionsVCDB : Table("version") {
    val version_date = datetime("VersionDate")
}

object Positions : Table("positions") {
    val id = integer("PositionID").primaryKey()
    val position = varchar("Position",100)
}