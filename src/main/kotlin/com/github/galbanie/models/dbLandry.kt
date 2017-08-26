package com.github.galbanie.models

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Created by Eurice on 17-08-26.
 */

//Eurice Landry

object PartToBaseVehicles : Table("PART_TO_BASE_VEHICLES"){
    val id = integer("PART_TO_BASE_VEHICLE_ID").primaryKey()
    val BaseVehicle_id = integer("BASE_VEHICLE_ID").references(BaseVehicles.id, ReferenceOption.CASCADE).primaryKey()
    val Part_id = integer("PARTS").references(Parts.id, ReferenceOption.CASCADE).primaryKey()
    val Position_id = integer("POSITIONS").references(Positions.id, ReferenceOption.CASCADE).primaryKey()
}

object Positions : Table("POSITIONS") {
    val id = integer("POSITION_ID").primaryKey()
    val Position = varchar("POSITION",255).nullable()
}

object Parts : Table("PARTS") {
    val id = integer("PART_ID").primaryKey()
}

object BaseVehicles : Table("BASE_VEHICLES") {
    val id = integer("BASE_VEHICLE_ID").primaryKey()
}

object VehicleTypes : Table("VEHIVLE_TYPES"){
    val id = integer("VEHIVLE_TYPE_ID").primaryKey()
    val Vehicle_Type = varchar("VEHICLE_TYPE",40).nullable()
}

object Models : Table("MODELS"){
    val id = integer("MODEL_ID").primaryKey()
    val name = varchar ("MODEL_NAME",255)
    val VehicleType_id = integer("VEHICLE_TYPE_ID").references(VehicleTypes.id, ReferenceOption.CASCADE).primaryKey()
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
    val numdoor = varchar ("BODY_NUM_DOOR",3)
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
    val blocktype = varchar ("ENGINE_BLOCK_TYPE",2)
    val engborein = varchar ("ENGINE_BASE_ENG_BORE_IN",10)
    val engboremetric = varchar ("ENGINE_BASE_ENG_BORE_METRIC",10)
    val engstrokein = varchar ("ENGINE_BASE_ENG_STROKE_IN",10)
    val engstrokemetric = varchar ("ENGINE_BASE_ENG_STROKE_METRIC",10)
}

object EngineDesignations : Table("ENGINE_DEISGNATIONS"){
    val id = integer("ENGINE_DEISGNATION_ID").primaryKey()
    val name = varchar ("ENGINE_DEISGNATION_NAME",50)
}

object EngineMfr : Table("ENGINE_MFRS"){
    val id = integer("ENGINE_MFR_ID").primaryKey()
    val name = varchar ("ENGINE_MFR_NAME",50)
}


object fuelDeliveryConfigurations : Table("FUEL_DELIVERY_CONFIGURATIONS"){
    val id = integer("FUEL_DELIVERY_CONFIGURATION_ID").primaryKey()
    val FuelDeliveryType_id = integer("FUEL_DELIVERY_TYPE_ID").references(FuelDeliveryTypes.id, ReferenceOption.CASCADE).primaryKey()
    val FuelDeliverySubType_id = integer("FUEL_DELIVERY_SUB_TYPE_ID").references(FuelDeliverySubTypes.id, ReferenceOption.CASCADE).primaryKey()
    val FuelSystemControlType_id = integer("FUEL_SYSTEM_CONTROL_TYPE_ID").references(FuelSystemControlTypes.id, ReferenceOption.CASCADE).primaryKey()
    val FuelSystemDesign_id = integer("FUEL_SYSTEM_DESIGN_ID").references(FuelSystemDesigns.id, ReferenceOption.CASCADE).primaryKey()

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

//Eurice Landry