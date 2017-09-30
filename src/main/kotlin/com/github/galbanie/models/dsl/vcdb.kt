package com.github.galbanie.models.dsl.vcdb

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Created by Galbanie on 2017-09-30.
 */
object BaseVehicles : Table("BaseVehicle") {
    val id = integer("BaseVehicleID").primaryKey()
    val year_id = integer("YearID").references(Years.id, ReferenceOption.CASCADE)
    val make_id = integer("MakeID").references(Makes.id, ReferenceOption.CASCADE)
    val model_id = integer("ModelID").references(Models.id, ReferenceOption.CASCADE)
}

object Years : Table("Year"){
    val id = integer("YearID").primaryKey()
}

object Makes : Table("Make"){
    val id = integer("MakeID").primaryKey()
    val name = varchar("MakeName",50)
}

object VehicleTypes : Table("VehicleType"){
    val id = integer("VehicleTypeID").primaryKey()
    val name = varchar("VehicleTypeName",50)
    val vehicleTypeGroup_id = integer("VehicleTypeGroupID").references(VehicleTypeGroups.id, ReferenceOption.CASCADE)
}

object Models : Table("Model"){
    val id = integer("ModelID").primaryKey()
    val name = varchar ("ModelName",100)
    val vehicleType_id = integer("VehicleTypeID").references(VehicleTypes.id, ReferenceOption.CASCADE)
}

object SubModels : Table("SubModel"){
    val id = integer("SubModelID").primaryKey()
    val name = varchar ("SubModelName",50)
}

object VehicleTypeGroups : Table("VehicleTypeGroup"){
    val id = integer("VehicleTypeGroupID").primaryKey()
    val name = varchar("VehicleTypeGroupName",50)
}

object MfrBodyCodes : Table("MfrBodyCode"){
    val id = integer("MfrBodyCodeID").primaryKey()
    val name = varchar ("MfrBodyCodeName",10)
}

object BodyNumDoors : Table("BodyNumDoors"){
    val id = integer("BodyNumDoorsID").primaryKey()
    val numDoor = varchar ("BodyNumDoors",3)
}

object BodyTypes : Table("BodyType"){
    val id = integer("BodyTypeID").primaryKey()
    val name = varchar ("BodyTypeName",50)
}

object DriveTypes : Table("DriveType"){
    val id = integer("DriveTypeID").primaryKey()
    val name = varchar ("DriveTypeName",30)
}

object EngineBases : Table("EngineBase"){
    val id = integer("EngineBaseID").primaryKey()
    val liter = varchar ("Liter",6)
    val cc = varchar ("CC",8)
    val cid = varchar ("CID",7)
    val cylinders = varchar ("Cylinders",2)
    val blockType = varchar ("BlockType",2)
    val engBoreIn = varchar ("EngBoreIn",10)
    val engBoreMetric = varchar ("EngBoreMetric",10)
    val engStrokeIn = varchar ("EngStrokeIn",10)
    val engStrokeMetric = varchar ("EngStrokeMetric",10)
}

object EngineDesignations : Table("EngineDesignation"){
    val id = integer("EngineDesignationID").primaryKey()
    val name = varchar ("EngineDesignationName",30)
}

object EngineMfrs : Table("EngineMfr"){
    val id = integer("EngineMfrID").primaryKey()
    val name = varchar ("EngineMfrName",50)
}

object FuelDeliveryConfigs : Table("FuelDeliveryConfig"){
    val id = integer("FuelDeliveryConfigID").primaryKey()
    val fuelDeliveryType_id = integer("FuelDeliveryTypeID").references(FuelDeliveryTypes.id, ReferenceOption.CASCADE)
    val fuelDeliverySubType_id = integer("FuelDeliverySubTypeID").references(FuelDeliverySubTypes.id, ReferenceOption.CASCADE)
    val fuelSystemControlType_id = integer("FuelSystemControlTypeID").references(FuelSystemControlTypes.id, ReferenceOption.CASCADE)
    val fuelSystemDesign_id = integer("FuelSystemDesignID").references(FuelSystemDesigns.id, ReferenceOption.CASCADE)
}

object FuelSystemDesigns : Table("FuelSystemDesign"){
    val id = integer("FuelSystemDesignID").primaryKey()
    val name = varchar ("FuelSystemDesignName",50)
}

object FuelSystemControlTypes : Table("FuelSystemControlType"){
    val id = integer("FuelSystemControlTypeID").primaryKey()
    val name = varchar ("FuelSystemControlTypeName",50)
}

object FuelDeliverySubTypes : Table("FuelDeliverySubType"){
    val id = integer("FuelDeliverySubTypeID").primaryKey()
    val name = varchar ("FuelDeliverySubTypeName",50)
}

object FuelDeliveryTypes : Table("FuelDeliveryType"){
    val id = integer("FuelDeliveryTypeID").primaryKey()
    val name = varchar ("FuelDeliveryTypeName",50)
}

object Aspirations : Table("Aspiration"){
    val id = integer("AspirationID").primaryKey()
    val name = varchar ("AspirationName",30)
}

object CylinderHeadTypes : Table("CylinderHeadType"){
    val id = integer("CylinderHeadTypeID").primaryKey()
    val name = varchar ("CylinderHeadTypeName",30)
}

object FuelTypes : Table("FuelType"){
    val id = integer("FuelTypeID").primaryKey()
    val name = varchar ("FuelTypeName",30)
}

object IgnitionSystemTypes : Table("IgnitionSystemType"){
    val id = integer("IgnitionSystemTypeID").primaryKey()
    val name = varchar ("IgnitionSystemTypeName",30)
}

object TransmissionTypes : Table("TransmissionType"){
    val id = integer("TransmissionTypeID").primaryKey()
    val name = varchar ("TransmissionTypeName",30)
}

object TransmissionControlTypes : Table("TransmissionControlType"){
    val id = integer("TransmissionControlTypeID").primaryKey()
    val name = varchar ("TransmissionControlTypeName",30)
}

object TransmissionMfrCodes : Table("TransmissionMfrCode"){
    val id = integer("TransmissionMfrCodeID").primaryKey()
    val name = varchar ("TransmissionMfrCode",30)
}

object TransmissionNumSpeeds : Table("TransmissionNumSpeeds"){
    val id = integer("TransmissionNumSpeedsID").primaryKey()
    val numbSpeed = varchar("TransmissionNumSpeeds",3)
}

object TransmissionBases : Table("TransmissionBase"){
    val id = integer("TransmissionBaseID").primaryKey()
    val transmissionType_id = integer("TransmissionTypeID").references(TransmissionTypes.id, ReferenceOption.CASCADE)
    val transmissionControlType_id = integer("TransmissionControlTypeID").references(TransmissionControlTypes.id, ReferenceOption.CASCADE)
    val transmissionNumSpeed_id = integer("TransmissionNumSpeedsID").references(TransmissionNumSpeeds.id, ReferenceOption.CASCADE)
}

object BedLengths : Table("BedLength"){
    val id = integer("BedLengthID").primaryKey()
    val name = varchar ("BedLength",10)
    val metric = varchar("BedLengthMetric",10)
}

object BedTypes : Table("BedType"){
    val id = integer("BedTypeID").primaryKey()
    val name = varchar ("BedTypeName",50)
}

object BedConfigs : Table("BedConfig"){
    val id = integer("BedConfigID").primaryKey()
    val bedLength_id = integer("BedLengthID").references(BedLengths.id, ReferenceOption.CASCADE)
    val bedType_id = integer("BedTypeID").references(BedTypes.id, ReferenceOption.CASCADE)
}

object WheelBases : Table("WheelBase"){
    val id = integer("WheelBaseID").primaryKey()
    val name = varchar ("WheelBaseName",10)
    val metric = varchar("WheelBaseMetric",10)
}

object BrakeConfigs : Table("BrakeConfig"){
    val id = integer("BrakeConfigID").primaryKey()
    val frontBrakeType_id = integer("FrontBrakeTypeID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val rearBrakeType_id = integer("RearBrakeTypeID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val brakeSystem_id = integer("BrakeSystemID").references(BrakeSystems.id, ReferenceOption.CASCADE)
    val brakeAbs_id = integer("BrakeABSID").references(BrakeAbs.id, ReferenceOption.CASCADE)
}

object BrakeSystems : Table("BrakeSystem"){
    val id = integer("BrakeSystemID").primaryKey()
    val name = varchar("BrakeSystemName",30)
}

object BrakeTypes : Table("BrakeType"){
    val id = integer("BrakeTypeID").primaryKey()
    val name = varchar("BrakeTypeName",30)
}

object BrakeAbs : Table("BrakeABS"){
    val id = integer("BrakeABSID").primaryKey()
    val name = varchar("BrakeABSName",30)
}

object SpringTypes : Table("SpringType"){
    val id = integer("SpringTypeID").primaryKey()
    val name = varchar("SpringTypeName",50)
}

object SpringTypeConfigs : Table("SpringTypeConfig"){
    val id = integer("SpringTypeConfigID").primaryKey()
    val frontSpringType_id = integer("FrontSpringTypeID").references(SpringTypes.id, ReferenceOption.CASCADE)
    val rearSpringType_id = integer("RearSpringTypeID").references(SpringTypes.id, ReferenceOption.CASCADE)
}

object SteeringTypes : Table("SteeringType"){
    val id = integer("SteeringTypeID").primaryKey()
    val name = varchar("SteeringTypeName",30)
}

object SteeringSystems : Table("SteeringSystem"){
    val id = integer("SteeringSystemID").primaryKey()
    val name = varchar("SteeringSystemName",30)
}

object Regions : Table("Region"){
    val id = integer("RegionID").primaryKey()
    val parentId = integer("ParentID").references(Regions.id, ReferenceOption.CASCADE)
    val name = varchar("RegionName",30)
    val nameAbbr = varchar("RegionAbbr",3)
}

object EngineVersions: Table("EngineVersion"){
    val id = integer("EngineVersionID").primaryKey()
    val version = varchar("EngineVersion",20)
}

// Object non existant dans la vcdb mais existe dans le diagramme de classe
object EngineValves: Table("ENGINE_VALVES"){
    val id = integer("ENGINE_VALVE_ID").primaryKey()
    val numbValve = integer("ENGINE_VALVE_NUMBER")
}

object PowerOutputs: Table("PowerOutput"){
    val id = integer("PowerOutputID").primaryKey()
    val horsePower = varchar("HorsePower",10)
    val kilowattPower = varchar("KilowattPower",10)
}

object PublicationStages: Table("PublicationStage"){
    val id = integer("PublicationStageID").primaryKey()
    val name = varchar("PublicationStageName",100)
}

object VehicleToBedConfigs: Table("VehicleToBedConfig"){
    val id = integer("VehicleToBedConfigID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val bedConfig_id = integer("BedConfigID").references(BedConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToMfrBodyCodes: Table("VehicleToMfrBodyCode"){
    val id = integer("VehicleToMfrBodyCodeID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val mfr_id = integer("MfrBodyCodeID").references(MfrBodyCodes.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToBodyStyleConfigs: Table("VehicleToBodyStyleConfig"){
    val id = integer("VehicleToBodyStyleConfigID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val bodyStyleConfig_id = integer("BodyStyleConfigID").references(BodyStyleConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToSpringTypeConfigs: Table("VehicleToSpringTypeConfig"){
    val id = integer("VehicleToSpringTypeConfigID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val springTypeConfig_id = integer("SpringTypeConfigID").references(SpringTypeConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToBrakeConfigs: Table("VehicleToBrakeConfig"){
    val id = integer("VehicleToBrakeConfigID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val brakeConfig_id = integer("BrakeConfigID").references(BrakeConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToSteeringConfigs: Table("VehicleToSteeringConfig"){
    val id = integer("VehicleToSteeringConfigID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val steeringConfig_id = integer("SteeringConfigID").references(SteeringConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToDriveTypes: Table("VehicleToDriveType"){
    val id = integer("VehicleToDriveTypeID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val driveType_id = integer("DriveTypeID").references(DriveTypes.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToTransmissions: Table("VehicleToTransmission"){
    val id = integer("VehicleToTransmissionID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val transmission_id = integer("TransmissionID").references(Transmissions.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToEngineConfigs: Table("VehicleToEngineConfig"){
    val id = integer("VehicleToEngineConfigID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val engineConfig_id = integer("EngineConfigID").references(EngineConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}

object VehicleToWheelbases: Table("VehicleToWheelbase"){
    val id = integer("VehicleToWheelbaseID").primaryKey()
    val vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val wheelbase_id = integer("WheelbaseID").references(WheelBases.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",255)
}

object Vehicles: Table("Vehicle"){
    val id = integer("VehicleID").primaryKey()
    val baseVehicle_id = integer("BaseVehicleID").references(BaseVehicles.id, ReferenceOption.CASCADE)
    val subModel_id = integer("SubModelID").references(SubModels.id, ReferenceOption.CASCADE)
    val region_id = integer("RegionID").references(Regions.id, ReferenceOption.CASCADE)
    val publicationStage_id = integer("PublicationStageID").references(PublicationStages.id, ReferenceOption.CASCADE)
    val publication_name = varchar("PublicationStageSource",100)
    val publication_date = date("PublicationStageDate")
    val source_name = varchar("Source",10)
    val publicationStageDate = date("PublicationStageDate")
}

object BodyStyleConfigs: Table("BodyStyleConfig"){
    val id = integer("BodyStyleConfigID").primaryKey()
    val bodyNumDoors_id = integer("BodyNumDoorsID").references(BodyNumDoors.id, ReferenceOption.CASCADE)
    val bodyType_id = integer("BodyTypeID").references(BodyTypes.id, ReferenceOption.CASCADE)
}

object SteeringConfigs: Table("SteeringConfig"){
    val id = integer("SteeringConfigID").primaryKey()
    val steeringType_id = integer("SteeringTypeID").references(SteeringTypes.id, ReferenceOption.CASCADE)
    val steeringSystem_id = integer("SteeringSystemID").references(SteeringSystems.id, ReferenceOption.CASCADE)
}

object Valves: Table("Valves"){
    val id = integer("ValvesID").primaryKey()
    val valvesPerEngine = varchar("ValvesPerEngine",3)
}

object Mfrs: Table("Mfr"){
    val id = integer("MfrID").primaryKey()
    val name = varchar("MfrName",30)
}

object EngineVINs: Table("EngineVIN"){
    val id = integer("EngineVINID").primaryKey()
    val name = varchar("EngineVINName",5)
}

object EngineConfigs: Table("EngineConfig"){
    val id = integer("EngineConfigID").primaryKey()
    val engineDesignation_id = integer("EngineDesignationID").references(EngineDesignations.id, ReferenceOption.CASCADE)
    val engineVIN_id = integer("EngineVINID").references(EngineVINs.id, ReferenceOption.CASCADE)
    val valves_id = integer("ValvesID").references(Valves.id, ReferenceOption.CASCADE)
    val engineBase_id = integer("EngineBaseID").references(EngineBases.id, ReferenceOption.CASCADE)
    val fuelDeliveryConfig_id = integer("FuelDeliveryConfigID").references(FuelDeliveryConfigs.id, ReferenceOption.CASCADE)
    val aspiration_id = integer("AspirationID").references(Aspirations.id, ReferenceOption.CASCADE)
    val cylinderHeadType_id = integer("CylinderHeadTypeID").references(CylinderHeadTypes.id, ReferenceOption.CASCADE)
    val fuelType_id = integer("FuelTypeID").references(FuelTypes.id, ReferenceOption.CASCADE)
    val ignitionSystemType_id = integer("IgnitionSystemTypeID").references(IgnitionSystemTypes.id, ReferenceOption.CASCADE)
    val engineMfr_id = integer("EngineMfrID").references(EngineMfrs.id, ReferenceOption.CASCADE)
    val engineVersion_id = integer("EngineVersionID").references(EngineVersions.id, ReferenceOption.CASCADE)
    val powerOutputId = integer("PowerOutputID")
}

object ElecControlleds: Table("ElecControlled"){
    val id = integer("ElecControlledID").primaryKey()
    val name = varchar("ElecControlled",3)
}

object TransmissionMfrs: Table("TransmissionMfr"){
    val id = integer("TransmissionMfrID").primaryKey()
    val name = varchar("TransmissionMfrName",255)
}

object Transmissions: Table("Transmission"){
    val id = integer("TransmissionID").primaryKey()
    val transmissionBase_id = integer("TransmissionBaseID").references(TransmissionBases.id, ReferenceOption.CASCADE)
    val transmissionMfrCode_id = integer("TransmissionMfrCodeID").references(TransmissionMfrCodes.id, ReferenceOption.CASCADE)
    val transmissionElecControlled_id = integer("TransmissionElecControlledID").references(ElecControlleds.id, ReferenceOption.CASCADE)
}

object VCDBChanges: Table("VCDBChanges"){
    val id = integer("id").primaryKey()
    val versionDate = date("versiondate")
    val name = varchar("tablename",30)
    val action = varchar("action",1)
}

object Versions: Table("Version"){
    val versionDate = date("VersionDate")
}

object Changes: Table("Changes"){
    val id = integer("ChangeID").primaryKey()
    val request_id = integer("RequestID")
    val versionDate = date("RevDate")
    val changeReason_id = integer("ChangeReasonID").references(ChangeReasons.id, ReferenceOption.CASCADE)
}

object ChangeReasons: Table("ChangeReasons"){
    val id = integer("ChangeReasonID").primaryKey()
    val name = varchar("ChangeReason",255)
}

object ChangeAttributeStates: Table("ChangeAttributeStates"){
    val id = integer("ChangeAttributeStateID").primaryKey()
    val name = varchar("ChangeAttributeState",255)
}

object ChangeTableNames: Table("ChangeTableNames"){
    val id = integer("TableNameID").primaryKey()
    val name = varchar("TableName",255)
    val name_Description = varchar("TableDescription",255)
}

object ChangeDetails: Table("ChangeDetails"){
    val id = integer("ChangeDetailID").primaryKey()
    val change_id = integer("ChangeID").references(Changes.id, ReferenceOption.CASCADE)
    val changeAttributeState_id = integer("ChangeAttributeStateID").references(ChangeAttributeStates.id, ReferenceOption.CASCADE)
    val tableName_id = integer("TableNameID").references(ChangeTableNames.id, ReferenceOption.CASCADE)
    val primaryKeyColumnName = varchar("PrimaryKeyColumnName",255)
    val primaryKeyBefore = integer("PrimaryKeyBefore")
    val primaryKeyAfter = integer("PrimaryKeyAfter")
    val columnName = varchar("ColumnName",255)
    val columnValueBefore = varchar("ColumnValueBefore",1000)
    val columnValueAfter = varchar("ColumnValueAfter",1000)
}

object Abbreviations: Table("Abbreviation"){
    val id = integer("Abbreviation").primaryKey()
    val name_Description = varchar("Description",20)
    val name_LongDescription = varchar("LongDescription",200)
}

object EnglishPhrases: Table("EnglishPhrase"){
    val id = integer("EnglishPhraseID").primaryKey()
    val englishPhrase = varchar("EnglishPhrase",100)
}

object Languages: Table("Language"){
    val id = integer("LanguageID").primaryKey()
    val language_name = varchar("LanguageName",20)
    val dialectName = varchar("DialectName",20)
}

object LanguageTranslations: Table("LanguageTranslation"){
    val id = integer("LanguageTranslationID").primaryKey()
    val name = varchar("Translation",150)
    val englishPhrase_id = integer("EnglishPhraseID").references(EnglishPhrases.id, ReferenceOption.CASCADE)
    val language_id = integer("LanguageID").references(Languages.id, ReferenceOption.CASCADE)
}

object LanguageTranslationAttachments: Table("LanguageTranslationAttachment"){
    val id = integer("LanguageTranslationAttachmentID").primaryKey()
    val languageTranslation_id = integer("LanguageTranslationID").references(LanguageTranslations.id, ReferenceOption.CASCADE)
    val attachment_id = integer("AttachmentID").references(Attachments.id, ReferenceOption.CASCADE)
}

object Attachments: Table("Attachment"){
    val id = integer("AttachmentID").primaryKey()
    val attachmentType_id = integer("AttachmentTypeID").references(AttachmentTypes.id, ReferenceOption.CASCADE)
    val attachmentFileName = varchar("AttachmentFileName",50)
    val attachmentURL = varchar("AttachmentURL",100)
    val attachmentDescription = varchar("AttachmentDescription",50)
}

object AttachmentTypes: Table("AttachmentType"){
    val id = integer("AttachmentTypeID").primaryKey()
    val name = varchar("AttachmentTypeName",20)
}

//https://github.com/JetBrains/Exposed

fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://localhost:3306/vcdb", driver = "com.mysql.jdbc.Driver",user = "root",password = "")
    transaction {
        Versions.selectAll().forEach {
            println(it[Versions.versionDate])
        }

    }
}