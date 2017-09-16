package com.github.galbanie.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Created by Galbanie on 2017-09-03.
 */

object Parts : Table("PARTS") {
    val id = integer("PART_ID").primaryKey()
}

object BaseVehicles : Table("BaseVehicle") {
    val id = integer("BaseVehicleID").primaryKey()
    val Year_id = integer("YearID").references(Years.id, ReferenceOption.CASCADE)
    val Make_id = integer("MakeID").references(Makes.id, ReferenceOption.CASCADE)
    val Model_id = integer("ModelID").references(Models.id, ReferenceOption.CASCADE)
}

object Years : Table("Year"){
    val id = integer("YearID").primaryKey()
}
object Makes : Table("Make"){
    val id = integer("MakeID").primaryKey()
    val name = varchar("MakeName",255)
}
object VehicleTypes : Table("VehicleType"){
    val id = integer("VehicleTypeID").primaryKey()
    val name = varchar("VehicleTypeName",255)
    val VehicleTypeGroup_id = integer("VehicleTypeGroupID").references(VehicleTypeGroups.id, ReferenceOption.CASCADE)
}
object Models : Table("Model"){
    val id = integer("ModelID").primaryKey()
    val name = varchar ("ModelName",255)
    val vehicleType_id = integer("VehicleTypeID").references(VehicleTypes.id, ReferenceOption.CASCADE)
}

object SubModels : Table("SubModel"){
    val id = integer("SubModelID").primaryKey()
    val name = varchar ("SubModelName",255)
}
object VehicleTypeGroups : Table("VehicleTypeGroup"){
    val id = integer("VehicleTypeGroupID").primaryKey()
    val name = varchar("VehicleTypeGroupName",255)
}
object MfrBodyCodes : Table("MfrBodyCode"){
    val id = integer("MfrBodyCodeID").primaryKey()
    val name = varchar ("MfrBodyCodeName",255)
}


object BodyNumDoors : Table("BodyNumDoors"){
    val id = integer("BodyNumDoorsID").primaryKey()
    val numDoor = varchar ("BodyNumDoors",10)
}

object BodyTypes : Table("BodyType"){
    val id = integer("BodyTypeID").primaryKey()
    val name = varchar ("BodyTypeName",255)
}

object DriveTypes : Table("DriveType"){
    val id = integer("DriveTypeID").primaryKey()
    val name = varchar ("DriveTypeName",50)
}

object EngineBases : Table("EngineBase"){
    val id = integer("EngineBaseID").primaryKey()
    val liter = varchar ("Liter",6)
    val cc = varchar ("cc",8)
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
    val name = varchar ("EngineDesignationName",50)
}

object EngineMfrs : Table("EngineMfr"){
    val id = integer("EngineMfrID").primaryKey()
    val name = varchar ("EngineMfrName",50)
}


object FuelDeliveryConfigs : Table("FuelDeliveryConfig"){
    val id = integer("FuelDeliveryConfigID").primaryKey()
    val FuelDeliveryType_id = integer("FuelDeliveryTypeID").references(FuelDeliveryTypes.id, ReferenceOption.CASCADE)
    val FuelDeliverySubType_id = integer("FuelDeliverySubTypeID").references(FuelDeliverySubTypes.id, ReferenceOption.CASCADE)
    val FuelSystemControlType_id = integer("FuelSystemControlTypeID").references(FuelSystemControlTypes.id, ReferenceOption.CASCADE)
    val FuelSystemDesign_id = integer("FuelSystemDesignID").references(FuelSystemDesigns.id, ReferenceOption.CASCADE)

}

object FuelSystemDesigns : Table("FuelSystemDesign"){
    val id = integer("FuelSystemDesignID").primaryKey()
    val name = varchar ("FuelSystemDesignName",255)
}

object FuelSystemControlTypes : Table("FuelSystemControlType"){
    val id = integer("FuelSystemControlTypeID").primaryKey()
    val name = varchar ("FuelSystemControlTypeName",255)
}

object FuelDeliverySubTypes : Table("FuelDeliverySubType"){
    val id = integer("FuelDeliverySubTypeID").primaryKey()
    val name = varchar ("FuelDeliverySubTypeName",255)
}

object FuelDeliveryTypes : Table("FuelDeliveryType"){
    val id = integer("FuelDeliveryTypeID").primaryKey()
    val name = varchar ("FuelDeliveryTypeName",255)
}

object Aspirations : Table("Aspiration"){
    val id = integer("AspirationID").primaryKey()
    val name = varchar ("AspirationName",30)
}

object CylinderHeadTypes : Table("CylinderHeadType"){
    val id = integer("CylinderHeadTypeID").primaryKey()
    val name = varchar ("CylinderHeadTypeName",255)
}

object FuelTypes : Table("FuelType"){
    val id = integer("FuelTypeID").primaryKey()
    val name = varchar ("FuelTypeName",255)
}
object IgnitionSystemTypes : Table("IgnitionSystemType"){
    val id = integer("IgnitionSystemTypeID").primaryKey()
    val name = varchar ("IgnitionSystemTypeName",255)
}
object TransmissionTypes : Table("TransmissionType"){
    val id = integer("TransmissionTypeID").primaryKey()
    val name = varchar ("TransmissionTypeName",255)
}
object TransmissionControlTypes : Table("TransmissionControlType"){
    val id = integer("TransmissionControlTypeID").primaryKey()
    val name = varchar ("TransmissionControlTypeName",255)
}
object TransmissionMfrCodes : Table("TransmissionMfrCode"){
    val id = integer("TransmissionMfrCodeID").primaryKey()
    val name = varchar ("TransmissionMfrCode",255)
}
object TransmissionNumSpeeds : Table("TransmissionNumSpeeds"){
    val id = integer("TransmissionNumSpeedsID").primaryKey()
    val numbSpeed = integer("TransmissionNumSpeeds")
}
object TransmissionBases : Table("TransmissionBase"){
    val id = integer("TransmissionBaseID").primaryKey()
    val TransmissionType_id = integer("TransmissionTypeID").references(TransmissionTypes.id, ReferenceOption.CASCADE)
    val TransmissionControlType_id = integer("TransmissionNumSpeedsID").references(TransmissionControlTypes.id, ReferenceOption.CASCADE)
    val TransmissionNumSpeed_id = integer("TransmissionControlTypeID").references(TransmissionNumSpeeds.id, ReferenceOption.CASCADE)
}

object BedLengths : Table("BedLength"){
    val id = integer("BedLengthID").primaryKey()
    val name = varchar ("BedLength",10)
    val metric = varchar("BedLengthMetric",10)
}
object BedTypes : Table("BedType"){
    val id = integer("BedTypeID").primaryKey()
    val name = varchar ("BedTypeName",255)

}
object BedConfigs : Table("BedConfig"){
    val id = integer("BedConfigID").primaryKey()
    val BedLength_id = integer("BedLengthID").references(BedLengths.id, ReferenceOption.CASCADE)
    val BedType_id = integer("BedTypeID").references(BedTypes.id, ReferenceOption.CASCADE)
}
object WheelBases : Table("WheelBase"){
    val id = integer("WheelBaseID").primaryKey()
    val name = varchar ("WheelBaseName",255)
    val metric = varchar("WheelBaseMetric",255)
}
object BrakeConfigs : Table("BrakeConfig"){
    val id = integer("BrakeConfigID").primaryKey()
    val FrontBrakeType_id = integer("FrontBrakeTypeID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val RearBrakeType_id = integer("RearBrakeTypeID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val BrakeSystem_id = integer("BrakeSystemID").references(BrakeSystems.id, ReferenceOption.CASCADE)
    val BrakeAbs_id = integer("BrakeABSID").references(BrakeAbs.id, ReferenceOption.CASCADE)
}
object BrakeSystems : Table("BrakeSystem"){
    val id = integer("BrakeSystemID").primaryKey()
    val name = varchar("BrakeSystemName",255)
}
object BrakeTypes : Table("BrakeType"){
    val id = integer("BrakeTypeID").primaryKey()
    val name = varchar("BrakeTypeName",255)
}
object BrakeAbs : Table("BrakeABS"){
    val id = integer("BrakeABSID").primaryKey()
    val name = varchar("BrakeABSName",255)
}
object SpringTypes : Table("SpringType"){
    val id = integer("SpringTypeID").primaryKey()
    val name = varchar("SpringTypeName",255)
}
object SpringTypeConfigs : Table("SpringTypeConfig"){
    val id = integer("SpringTypeConfigID").primaryKey()
    val FrontSpringType_id = integer("FrontSpringTypeID").references(SpringTypes.id, ReferenceOption.CASCADE)
    val RearSpringType_id = integer("RearSpringTypeID").references(SpringTypes.id, ReferenceOption.CASCADE)
}
object SteeringTypes : Table("SteeringType"){
    val id = integer("SteeringTypeID").primaryKey()
    val name = varchar("SteeringTypeName",255)
}
object SteeringSystems : Table("SteeringSystem"){
    val id = integer("SteeringSystemID").primaryKey()
    val name = varchar("SteeringSystemName",255)
}

object Regions : Table("Region"){
    val id = integer("RegionID").primaryKey()
    val parentId = integer("ParentID")
    val name = varchar("RegionName",255)
    val nameAbbr = varchar("RegionAbbr",255)
}
object EngineVersions: Table("EngineVersion"){
    val id = integer("EngineVersionID").primaryKey()
    val version = varchar("EngineVersion",255)
}
object EngineValves: Table("ENGINE_VALVES"){
    val id = integer("ENGINE_VALVE_ID").primaryKey()
    val numbValve = integer("ENGINE_VALVE_NUMBER")
}
object PowerOutputs: Table("PowerOutput"){
    val id = integer("PowerOutputID").primaryKey()
    val horsePower = varchar("HorsePower",255)
    val kilowattPower = varchar("KilowattPower",255)
}
object PublicationStages: Table("PublicationStage"){
    val id = integer("PublicationStageID").primaryKey()
    val name = varchar("PublicationStageName",255)
}
object VehicleToBedConfigs: Table("VehicleToBedConfig"){
    val id = integer("VehicleToBedConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val BedConfig_id = integer("BedConfigID").references(BedConfigs.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToMfrBodyCodes: Table("VehicleToMfrBodyCode"){
    val id = integer("VehicleToMfrBodyCodeID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val Mfr_id = integer("MfrBodyCodeID").references(MfrBodyCodes.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToBodyStyleConfigs: Table("VehicleToBodyStyleConfig"){
    val id = integer("VehicleToBodyStyleConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val BodyStyleConfig_id = integer("BodyStyleConfigID").references(BodyStyleConfigs.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToSpringTypeConfigs: Table("VehicleToSpringTypeConfig"){
    val id = integer("VehicleToSpringTypeConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val SpringTypeConfig_id = integer("SpringTypeConfigID").references(SpringTypeConfigs.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToBrakeConfigs: Table("VehicleToBrakeConfig"){
    val id = integer("VehicleToBrakeConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val BrakeConfig_id = integer("BrakeConfigID").references(BrakeConfigs.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToSteeringConfigs: Table("VehicleToSteeringConfig"){
    val id = integer("VehicleToSteeringConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val SteeringConfig_id = integer("SteeringConfigID").references(SteeringConfigs.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToDriveTypes: Table("VehicleToDriveType"){
    val id = integer("VehicleToDriveTypeID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val DriveType_id = integer("DriveTypeID").references(DriveTypes.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToTransmissions: Table("VehicleToTransmission"){
    val id = integer("VehicleToTransmissionID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val Transmission_id = integer("TransmissionID").references(Transmissions.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToEngineConfigs: Table("VehicleToEngineConfig"){
    val id = integer("VehicleToEngineConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val EngineConfig_id = integer("EngineConfigID").references(EngineConfigs.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToWheelbases: Table("VehicleToWheelbase"){
    val id = integer("VehicleToWheelbaseID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val Wheelbase_id = integer("WheelbaseID").references(WheelBases.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object Vehicles: Table("Vehicle"){
    val id = integer("VehicleID").primaryKey()
    val Vehicle_id = integer("BaseVehicleID").references(BaseVehicles.id, ReferenceOption.CASCADE)
    val Wheelbase_id = integer("SubModelID").references(SubModels.id, ReferenceOption.CASCADE)
    val Region_id = integer("RegionID").references(Regions.id, ReferenceOption.CASCADE)
    val PublicationStage_id = integer("PublicationStageID").references(PublicationStages.id, ReferenceOption.CASCADE)
    val Publication_name = varchar("PublicationStageSource",100)
    val Publication_date = date("PublicationStageDate")
}
object BodyStyleConfigs: Table("BodyStyleConfig"){
    val id = integer("BodyStyleConfigID").primaryKey()
    val BodyNumDoors_id = integer("BodyNumDoorsID").references(BodyNumDoors.id, ReferenceOption.CASCADE)
    val BodyType_id = integer("BodyTypeID").references(BodyTypes.id, ReferenceOption.CASCADE)
}
object SteeringConfigs: Table("SteeringConfig"){
    val id = integer("SteeringConfigID").primaryKey()
    val SteeringType_id = integer("SteeringTypeID").references(SteeringTypes.id, ReferenceOption.CASCADE)
    val SteeringSystem_id = integer("SteeringSystemID").references(SteeringSystems.id, ReferenceOption.CASCADE)
}
object Valves: Table("Valves"){
    val id = integer("ValvesID").primaryKey()
    val valvesPerEngine = integer("ValvesPerEngine")
}
object Mfrs: Table("Mfr"){
    val id = integer("MfrID").primaryKey()
    val name = varchar("MfrName",255)
}
object EngineVINs: Table("EngineVIN"){
    val id = integer("EngineVINID").primaryKey()
    val name = varchar("EngineVINName",255)
}
object EngineConfigs: Table("EngineConfig"){
    val id = integer("EngineConfigID").primaryKey()
    val EngineDesignation_id = integer("EngineDesignationID").references(EngineDesignations.id, ReferenceOption.CASCADE)
    val EngineVIN_id = integer("EngineVINID").references(EngineVINs.id, ReferenceOption.CASCADE)
    val Valves_id = integer("ValvesID").references(Valves.id, ReferenceOption.CASCADE)
    val EngineBase_id = integer("EngineBaseID").references(EngineBases.id, ReferenceOption.CASCADE)
    val FuelDeliveryConfig_id = integer("FuelDeliveryConfigID").references(FuelDeliveryConfigs.id, ReferenceOption.CASCADE)
    val Aspiration_id = integer("AspirationID").references(Aspirations.id, ReferenceOption.CASCADE)
    val CylinderHeadType_id = integer("CylinderHeadTypeID").references(CylinderHeadTypes.id, ReferenceOption.CASCADE)
    val FuelType_id = integer("FuelTypeID").references(FuelTypes.id, ReferenceOption.CASCADE)
    val IgnitionSystemType_id = integer("IgnitionSystemTypeID").references(IgnitionSystemTypes.id, ReferenceOption.CASCADE)
    val EngineMfr_id = integer("EngineMfrID").references(EngineMfrs.id, ReferenceOption.CASCADE)
    val EngineVersion_id = integer("EngineVersionID").references(EngineVersions.id, ReferenceOption.CASCADE)
    val PowerOutput_id = integer("PowerOutputID").references(PowerOutputs.id, ReferenceOption.CASCADE)
}
object TransmissionElecControlleds: Table("TransmissionElecControlled"){
    val id = integer("TransmissionElecControlledID").primaryKey()
    val name = varchar("TransmissionElecControlled",255)
}
object TransmissionMfrs: Table("TransmissionMfr"){
    val id = integer("TransmissionMfrID").primaryKey()
    val name = varchar("TransmissionMfrName",255)
}
object Transmissions: Table("Transmission"){
    val id = integer("TransmissionID").primaryKey()
    val TransmissionBase_id = integer("TransmissionBaseID").references(TransmissionBases.id, ReferenceOption.CASCADE)
    val TransmissionMfrCode_id = integer("TransmissionMfrCodeID").references(TransmissionMfrCodes.id, ReferenceOption.CASCADE)
    val TransmissionElecControlled_id = integer("TransmissionElecControlledID").references(TransmissionElecControlleds.id, ReferenceOption.CASCADE)
    val TransmissionMfr_id = integer("TransmissionMfrID").references(TransmissionMfrs.id, ReferenceOption.CASCADE)
}
object VCDBChanges: Table("VCDBChanges"){
    val id = integer("Id").primaryKey()
    val versionDate = date("VersionDate")
    val name = varchar("TableName",255)
    val action = varchar("Action",255)
}
object Versions: Table("Version"){
    val versionDate = date("VersionDate")
}
object Changes: Table("Changes"){
    val id = integer("ChangeID").primaryKey()
    val Request_id = integer("RequestID")
    val versionDate = date("RevDate")
    val ChangeReason_id = integer("ChangeReasonID").references(ChangeReasons.id, ReferenceOption.CASCADE)
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
    val Change_id = integer("ChangeID").references(Changes.id, ReferenceOption.CASCADE)
    val ChangeAttributeState_id = integer("ChangeAttributeStateID").references(ChangeAttributeStates.id, ReferenceOption.CASCADE)
    val TableName_id = integer("TableNameID").references(ChangeTableNames.id, ReferenceOption.CASCADE)
    val PrimaryKeyColumnName = integer("PrimaryKeyColumnName")
    val PrimaryKeyBefore = integer("PrimaryKeyBefore")
    val PrimaryKeyAfter = integer("PrimaryKeyAfter")
    val ColumnName = varchar("ColumnName",255)
    val ColumnValueBefore = varchar("ColumnValueBefore",255)
    val ColumnValueAfter = varchar("ColumnValueAfter",255)
}
object Abbreviations: Table("Abbreviation"){
    val id = integer("Abbreviation").primaryKey()
    val name_Description = varchar("Description",20)
    val name_LongDescription = varchar("LongDescription",200)
}
object EnglishPhrases: Table("EnglishPhrase"){
    val id = integer("EnglishPhraseID").primaryKey()
    val EnglishPhrase = varchar("EnglishPhrase",255)
}
object Languages: Table("Language"){
    val id = integer("LanguageID").primaryKey()
    val Language_name = varchar("Language",255)
    val DialectName = varchar("DialectName",255)
}
object LanguageTranslations: Table("LanguageTranslation"){
    val id = integer("LanguageTranslationID").primaryKey()
    val name = varchar("Translation",255)
    val EnglishPhrase_id = integer("EnglishPhraseID").references(EnglishPhrases.id, ReferenceOption.CASCADE)
    val Language_id = integer("LanguageID").references(Languages.id, ReferenceOption.CASCADE)
}
object LanguageTranslationAttachments: Table("LanguageTranslationAttachment"){
    val id = integer("LanguageTranslationAttachmentID").primaryKey()
    val LanguageTranslation_id = integer("LanguageTranslationID").references(LanguageTranslations.id, ReferenceOption.CASCADE)
    val Attachment_id = integer("AttachmentID").references(Attachments.id, ReferenceOption.CASCADE)
}
object Attachments: Table("Attachment"){
    val id = integer("AttachmentID").primaryKey()
    val AttachmentType_id = integer("AttachmentTypeID").references(AttachmentTypes.id, ReferenceOption.CASCADE)
    val AttachmentFileName = varchar("AttachmentFileName",50)
    val AttachmentURL = varchar("AttachmentURL",100)
    val AttachmentDescription = varchar("AttachmentDescription",50)
}
object AttachmentTypes: Table("AttachmentType"){
    val id = integer("AttachmentTypeID").primaryKey()
    val name = varchar("AttachmentTypeName",20)
}