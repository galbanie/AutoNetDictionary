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

object BodyConfigurations : Table("BODY_CONFIGURATIONS"){
    val id = integer("BODY_CONFIGURATION_ID").primaryKey()
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

object EngineBases : Table("ENGINE_BASES"){
    val id = integer("ENGINE_BASE_ID").primaryKey()
    val liter = varchar ("ENGINE_BASE_LITER",6)
    val cc = varchar ("ENGINE_BASE_CC",8)
    val cid = varchar ("ENGINE_BASE_CID",7)
    val cylinders = varchar ("ENGINE_BASE_CYLINDER",2)
    val blockType = varchar ("ENGINE_BLOCK_TYPE",2)
    val engBoreIn = varchar ("ENGINE_BORE_IN",10)
    val engBoreMetric = varchar ("ENGINE_BORE_METRIC",10)
    val engStrokeIn = varchar ("ENGINE_STROKE_IN",10)
    val engStrokeMetric = varchar ("ENGINE_BASE_ENG_STROKE_METRIC",10)
}

object EngineDesignations : Table("ENGINE_DESIGNATIONS"){
    val id = integer("ENGINE_DESIGNATION_ID").primaryKey()
    val name = varchar ("ENGINE_DESIGNATION_NAME",50)
}

object EngineMfr : Table("ENGINE_MFRS"){
    val id = integer("ENGINE_MFR_ID").primaryKey()
    val name = varchar ("ENGINE_MFR_NAME",50)
}


object FuelDeliveryConfigurations : Table("FUEL_DELIVERY_CONFIGURATIONS"){
    val id = integer("FUEL_DELIVERY_CONFIGURATION_ID").primaryKey()
    val FuelDeliveryType_id = integer("FUEL_DELIVERY_TYPE_ID").references(FuelDeliveryTypes.id, ReferenceOption.CASCADE)
    val FuelDeliverySubType_id = integer("FUEL_DELIVERY_SUB_TYPE_ID").references(FuelDeliverySubTypes.id, ReferenceOption.CASCADE)
    val FuelSystemControlType_id = integer("FUEL_SYSTEM_CONTROL_TYPE_ID").references(FuelSystemControlTypes.id, ReferenceOption.CASCADE)
    val FuelSystemDesign_id = integer("FUEL_SYSTEM_DESIGN_ID").references(FuelSystemDesigns.id, ReferenceOption.CASCADE)

}

object FuelSystemDesigns : Table("FUEL_SYSTEM_DESIGNS"){
    val id = integer("FUEL_SYSTEM_DESIGN_ID").primaryKey()
}

object FuelSystemControlTypes : Table("FUEL_SYSTEM_CONTROL_TYPES"){
    val id = integer("FUEL_SYSTEM_CONTROL_TYPE_ID").primaryKey()
}

object FuelDeliverySubTypes : Table("FUEL_DELIVERY_SUB_TYPES"){
    val id = integer("FUEL_DELIVERY_SUB_TYPE_ID").primaryKey()
}

object FuelDeliveryTypes : Table("FUEL_DELIVERY_TYPES"){
    val id = integer("FUEL_DELIVERY_TYPE_ID").primaryKey()
}

object Aspirations : Table("ASPIRATIONS"){
    val id = integer("ASPIRATION_ID").primaryKey()
    val name = varchar ("ASPIRATION_NAME",255)
}

object CylinderHeadTypes : Table("CYLINDER_HEAD_TYPES"){
    val id = integer("CYLINDER_HEAD_TYPE_ID").primaryKey()
    val name = varchar ("CYLINDER_HEAD_TYPE_NAME",255)
}

object FuelTypes : Table("FUEL_TYPES"){
    val id = integer("FUEL_TYPE_ID").primaryKey()
    val name = varchar ("FUEL_TYPE_NAME",255)
}
object IgnitionSystemTypes : Table("IGNITION_SYSTEM_TYPES"){
    val id = integer("IGNITION_SYSTEM_TYPE_ID").primaryKey()
    val name = varchar ("IGNITION_SYSTEM_TYPE_NAME",255)
}
object TransmissionTypes : Table("TRANSMISSION_TYPES"){
    val id = integer("TRANSMISSION_TYPE_ID").primaryKey()
    val name = varchar ("TRANSMISSION_TYPE_NAME",255)
}
object TransmissionControlTypes : Table("TRANSMISSION_CONTROL_TYPES"){
    val id = integer("TRANSMISSION_CONTROL_TYPE_ID").primaryKey()
    val name = varchar ("TRANSMISSION_CONTROL_TYPE_NAME",255)
}
object TransmissionMfrCodes : Table("TRANSMISSION_MFR_CODES"){
    val id = integer("TRANSMISSION_MFR_CODE_ID").primaryKey()
    val name = varchar ("TRANSMISSION_MFR_CODE_NAME",255)
}
object TransmissionNumSpeeds : Table("TRANSMISSION_NUM_SPEEDS"){
    val id = integer("TRANSMISSION_NUM_SPEED_ID").primaryKey()
    val numbSpeed = integer("TRANSMISSION_NUM_SPEED_NUMBER")
}
object TransmissionBases : Table("TRANSMISSION_BASES"){
    val id = integer("TRANSMISSION_BASE_ID").primaryKey()
    val TransmissionType_id = integer("TRANSMISSION_TYPE_ID").references(TransmissionTypes.id, ReferenceOption.CASCADE)
    val TransmissionControlType_id = integer("TRANSMISSION_CONTROL_TYPE_ID").references(TransmissionControlTypes.id, ReferenceOption.CASCADE)
    val TransmissionNumSpeed_id = integer("TRANSMISSION_NUM_SPEED_ID").references(TransmissionNumSpeeds.id, ReferenceOption.CASCADE)
}
object TransfertCases : Table("TRANSFERT_CASES"){
    val id = integer("TRANSFERT_CASE_ID").primaryKey()

}
object BedLengths : Table("BedLength"){
    val id = integer("BedLengthID").primaryKey()
    val name = varchar ("BedLength",255)
    val metric = varchar("BedLengthMetric",255)
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
object BrakeConfigs : Table("BRAKE_CONFIGS"){
    val id = integer("BRAKE_CONFIG_ID").primaryKey()
    val FrontBrakeType_id = integer("FRONT_BRAKE_TYPE_ID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val RearBrakeType_id = integer("REAR_BRAKE_TYPE_ID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val BrakeSystem_id = integer("BRAKE_SYSTEM_ID").references(BrakeSystems.id, ReferenceOption.CASCADE)
    val BrakeAbs_id = integer("BRAKE_ABS_ID").references(BrakeAbs.id, ReferenceOption.CASCADE)
}
object BrakeSystems : Table("BRAKE_SYSTEMS"){
    val id = integer("BRAKE_SYSTEM_ID").primaryKey()
    val name = varchar("BRAKE_SYSTEM_NAME",255)
}
object BrakeTypes : Table("BRAKE_TYPES"){
    val id = integer("BRAKE_TYPE_ID").primaryKey()
    val name = varchar("BRAKE_TYPE_NAME",255)
}
object BrakeAbs : Table("BRAKE_ABS"){
    val id = integer("BRAKE_ABS_ID").primaryKey()
    val name = varchar("BRAKE_ABS_NAME",255)
}
object SpringTypes : Table("SPRING_TYPES"){
    val id = integer("SPRING_TYPE_ID").primaryKey()
    val name = varchar("SPRING_TYPE_NAME",255)
}
object SpringTypeConfigs : Table("SPRING_TYPE_CONFIGS"){
    val id = integer("SPRING_TYPE_CONFIG_ID").primaryKey()
    val FrontSpringType_id = integer("FRONT_SPRING_TYPE_ID").references(SpringTypes.id, ReferenceOption.CASCADE)
    val RearSpringType_id = integer("REAR_SPRING_TYPE_ID").references(SpringTypes.id, ReferenceOption.CASCADE)
}
object SteeringTypes : Table("STEERING_TYPES"){
    val id = integer("STEERING_TYPE_ID").primaryKey()
    val name = varchar("STEERING_TYPE_NAME",255)
}
object SteeringSystems : Table("STEERING_SYSTEMS"){
    val id = integer("STEERING_SYSTEM_ID").primaryKey()
    val name = varchar("STEERING_SYSTEM_NAME",255)
}
object RestraintTypes : Table("RESTRAINT_TYPES"){
    val id = integer("RESTRAINT_TYPE_ID").primaryKey()

}
object Regions : Table("Region"){
    val id = integer("RegionID").primaryKey()
    val parentId = integer("ParentID")
    val name = varchar("RegionName",255)
    val nameAbbr = varchar("RegionAbbr",255)
}
object EngineVersions: Table("ENGINE_VERSIONS"){
    val id = integer("ENGINE_VERSION_ID").primaryKey()
    val version = varchar("ENGINE_VERSION_NAME",255)
}
object EngineValves: Table("ENGINE_VALVES"){
    val id = integer("ENGINE_VALVE_ID").primaryKey()
    val numbValve = integer("ENGINE_VALVE_NUMBER")
}
object PowerOutputs: Table("POWER_OUTPUTS"){
    val id = integer("POWER_OUTPUT_ID").primaryKey()
    val horsePower = varchar("HORSE_POWER",255)
    val kilowattPower = varchar("KILOWATT_POWER",255)
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
    val SteeringConfig_id = integer("SteeringConfigID").references(VehicleToSteeringConfigs.id, ReferenceOption.CASCADE)
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
    //val Transmission_id = integer("TransmissionID").references(Transmissions.id, ReferenceOption.CASCADE)
    val name = varchar("Source",255)
}
object VehicleToEngineConfigs: Table("VehicleToEngineConfig"){
    val id = integer("VehicleToEngineConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    //val EngineConfig_id = integer("EngineConfigID").references(EngineConfigs.id, ReferenceOption.CASCADE)
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
