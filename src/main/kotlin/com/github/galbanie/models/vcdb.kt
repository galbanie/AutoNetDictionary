package com.github.galbanie.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Created by Galbanie on 2017-09-03.
 */

object Parts : Table("PARTS") {
    val id = integer("PART_ID").primaryKey()
}

object BaseVehicles : Table("BASE_VEHICLES") {
    val id = integer("BASE_VEHICLE_ID").primaryKey()
}

object VehicleTypes : Table("VEHICLE_TYPES"){
    val id = integer("VEHICLE_TYPE_ID").primaryKey()
    val vehicleType = varchar("VEHICLE_TYPE",40)
}

object Models : Table("MODELS"){
    val id = integer("MODEL_ID").primaryKey()
    val name = varchar ("MODEL_NAME",255)
    val vehicleType_id = integer("VEHICLE_TYPE_ID").references(VehicleTypes.id, ReferenceOption.CASCADE)
}

object SubModels : Table("SUB_MODELS"){
    val id = integer("SUB_MODEL_ID").primaryKey()
    val name = varchar ("SUB_MODEL_NAME",50)
}

object MfrBodyCodes : Table("MFR_BODY_CODES"){
    val id = integer("MFR_BODY_CODE_ID").primaryKey()
    val name = varchar ("MFR_BODY_CODE_NAME",255)
}

object BodyConfigurations : Table("BODY_CONFIGURATIONS"){
    val id = integer("BODY_CONFIGURATION_ID").primaryKey()
}

object BodyNumDoors : Table("BODY_NUM_DOORS"){
    val id = integer("BODY_NUM_DOOR_ID").primaryKey()
    val numDoor = varchar ("BODY_NUM_DOOR",3)
}

object BodyTypes : Table("BODY_TYPES"){
    val id = integer("BODY_TYPE_ID").primaryKey()
    val name = varchar ("BODY_TYPE_NAME",255)
}

object DriveTypes : Table("DRIVE_TYPES"){
    val id = integer("DRIVE_TYPE_ID").primaryKey()
    val name = varchar ("DRIVE_TYPE_NAME",50)
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
object BedLengths : Table("BED_LENGTHS"){
    val id = integer("BED_LENGTH_ID").primaryKey()
    val name = varchar ("BED_LENGTH_NAME",255)
    val metric = varchar("BED_LENGTH_METRIC",255)
}
object BedTypes : Table("BED_TYPES"){
    val id = integer("BED_TYPE_ID").primaryKey()
    val name = varchar ("BED_TYPE_NAME",255)

}
object BedConfigs : Table("BED_CONFIGS"){
    val id = integer("BED_CONFIG_ID").primaryKey()
    val BedLength_id = integer("BED_LENGTH_ID").references(BedLengths.id, ReferenceOption.CASCADE)
    val BedType_id = integer("BED_TYPE_ID").references(BedTypes.id, ReferenceOption.CASCADE)
}
object WheelBases : Table("WHEEL_BASES"){
    val id = integer("WHEEL_BASE_ID").primaryKey()
    val name = varchar ("WHEEL_BASE_NAME",255)
    val metric = varchar("WHEEL_BASE_METRIC",255)
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
object Regions : Table("REGIONS"){
    val id = integer("REGION_ID").primaryKey()
    val parentId = integer("PARENT_REGION_ID")
    val name = varchar("REGION_NAME",255)
    val nameAbbr = varchar("REGION_ABBR_NAME",255)
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