package com.github.galbanie.models

import com.github.galbanie.utils.ParameterType
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

/**
 * Created by Galbanie on 2017-09-03.
 */
object Sessions : Table("SESSIONS"){
    val id = integer("SESSION_ID").primaryKey().autoIncrement()
    val user_id = integer("USER_ID").references(Users.id, ReferenceOption.CASCADE)
    val isUserProcess = bool("IS_USER_PROCESS").default(false)
}

object HistoricSessions : Table("HISTORIC_SESSIONS"){
    val id = integer("HISTORIC_SESSION_ID").primaryKey().autoIncrement()
    val session_id = integer("SESSION_ID").references(Sessions.id, ReferenceOption.NO_ACTION)
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
    val user_id = integer("USER_ID").references(Users.id, ReferenceOption.CASCADE)
    val role_id = integer("ROLE_ID").references(Roles.id, ReferenceOption.CASCADE)
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
    val input_id = integer("INPUT").references(Parameters.id, ReferenceOption.CASCADE).primaryKey()
    val output_id = integer("OUTPUT").references(Parameters.id, ReferenceOption.CASCADE).primaryKey()
    val valid = bool("VALID").default(false)
    val creator_id = integer("USER_ID").references(Users.id, ReferenceOption.SET_NULL)
    val reporter_id = integer("USER_ID").references(Users.id, ReferenceOption.SET_NULL)
    val dateCreation = date("DATE_CREATION").nullable()
    val dateModified = date("DATE_MODIFIED").nullable()
}

object ActionsParameters : Table("ACTIONS_PARAMETERS"){
    val param_id = integer("PARAMETER_ID").references(Parameters.id, ReferenceOption.CASCADE)
    val action_id = integer("ACTION_ID").references(Actions.id, ReferenceOption.CASCADE)
    val priority = integer("PRIORITY")
}

object Applications : Table("APPLICATIONS"){
    val applications_id = integer("APPLICATION_ID").primaryKey().autoIncrement()
    val partToBaseVehicle_id = integer("PART_BASE_VEHICLE_ID").references(PartToBaseVehicles.id, ReferenceOption.SET_NULL)
    val vehicleType_id = integer("VEHICLE_TYPE_ID").references(VehicleTypes.id, ReferenceOption.SET_NULL)
    val model_id = integer("MODEL_ID").references(Models.id, ReferenceOption.SET_NULL)
    val subModel_id = integer("SUB_MODEL_ID").references(SubModels.id, ReferenceOption.SET_NULL)
    val mfrBodyCode_id = integer("MFR_BODY_CODE_ID").references(MfrBodyCodes.id, ReferenceOption.SET_NULL)
    val bodyConfig_id = integer("BODY_CONFIG_ID").references(BodyConfigurations.id, ReferenceOption.SET_NULL)
    val bodyNumDoors_id = integer("BODY_NUM_DOORS_ID").references(BodyNumDoors.id, ReferenceOption.SET_NULL)
    val bodyType_id = integer("BODY_TYPE_ID").references(BodyTypes.id, ReferenceOption.SET_NULL)
    val driveType_id = integer("DRIVE_TYPE_ID").references(DriveTypes.id, ReferenceOption.SET_NULL)
    val engineBase_id = integer("ENGINE_BASE_ID").references(EngineBases.id, ReferenceOption.SET_NULL)
    val engineDesignation_id = integer("ENGINE_DESIGNATION_ID").references(EngineDesignations.id, ReferenceOption.SET_NULL)
    val engineVIN_id = integer("ENGINE_VIN_ID")
    val engineMfr_id = integer("ENGINE_MFR_ID").references(EngineMfr.id, ReferenceOption.SET_NULL)
    val fuelDeliveryConfig_id = integer("FUEL_DELIVERY_CONFIG_ID").references(FuelDeliveryConfigurations.id, ReferenceOption.SET_NULL)
    val fuelDeliveryType_id = integer("FUEL_DELIVERY_TYPE_ID").references(FuelDeliveryTypes.id, ReferenceOption.SET_NULL)
    val fuelDeliverySubType_id = integer("FUEL_DELIVERY_SUBTYPE_ID").references(FuelDeliverySubTypes.id, ReferenceOption.SET_NULL)
    val fuelSysControlType_id = integer("FUEL_SYS_CONTROL_TYPE_ID").references(FuelSystemControlTypes.id, ReferenceOption.SET_NULL)
    val fuelSystemDesign_id = integer("FUEL_SYSTEM_DESIGN_ID").references(FuelSystemDesigns.id, ReferenceOption.SET_NULL)
    val aspiration_id = integer("ASPIRATION_ID").references(Aspirations.id, ReferenceOption.SET_NULL)
    val cylHeadType_id = integer("CYL_HEAD_TYPE_ID").references(CylinderHeadTypes.id, ReferenceOption.SET_NULL)
    val fuelType_id = integer("FUEL_TYPE_ID").references(FuelTypes.id, ReferenceOption.SET_NULL)
    val ignitionSystemType_id = integer("IGNITION_SYSTEM_TYPE_ID").references(IgnitionSystemTypes.id, ReferenceOption.SET_NULL)
    val transmissionType_id = integer("TRANSMISSION_TYPE_ID").references(TransmissionTypes.id, ReferenceOption.SET_NULL)
    val transmissionBase_id = integer("TRANSMISSION_BASE_ID").references(TransmissionBases.id, ReferenceOption.SET_NULL)
    val transmissionControlType_id = integer("TRANSMISSION_CONTROL_TYPE_ID").references(TransmissionControlTypes.id, ReferenceOption.SET_NULL)
    val transmissionMfrCode_id = integer("TRANSMISSION_MFR_CODE_ID").references(TransmissionMfrCodes.id, ReferenceOption.SET_NULL)
    val transmissionNumSpeeds_id = integer("TRANSMISSION_NUM_SPEEDS_ID").references(TransmissionNumSpeeds.id, ReferenceOption.SET_NULL)
    val transfertCase_id = integer("TRANSFERT_CASE_ID").references(TransfertCases.id, ReferenceOption.SET_NULL)
    val bedLength_id = integer("BED_LENGTH_ID").references(BedLengths.id, ReferenceOption.SET_NULL)
    val bedType_id = integer("BED_TYPE_ID").references(BedTypes.id, ReferenceOption.SET_NULL)
    val bedConfig_id = integer("BED_CONFIG_ID").references(BedConfigs.id, ReferenceOption.SET_NULL)
    val wheelBase_id = integer("WHEEL_BASE_ID").references(WheelBases.id, ReferenceOption.SET_NULL)
    val frontBrakeType_id = integer("FRONT_BRAKE_TYPE_ID").references(BrakeTypes.id, ReferenceOption.SET_NULL)
    val rearBrakeType_id = integer("REAR_BRAKE_TYPE_ID").references(BrakeTypes.id, ReferenceOption.SET_NULL)
    val frontSpringType_id = integer("FRONT_SPRING_TYPE_ID").references(SpringTypes.id, ReferenceOption.SET_NULL)
    val brakeSystem_id = integer("MODEL_ID")
    val brakeType_id = integer("MODEL_ID")
    val brakeABS_id = integer("MODEL_ID")
    val rearSpringType_id = integer("REAR_SPRING_TYPE_ID").references(SpringTypes.id, ReferenceOption.SET_NULL)
    val steeringType_id = integer("MODEL_ID")
    val steeringSystem_id = integer("MODEL_ID")
    val restraintType_id = integer("MODEL_ID")
    val regionAbbr_id = integer("MODEL_ID")
    val engineVersion_id = integer("MODEL_ID")
    val engineValves_id = integer("MODEL_ID")
    /*`Note1` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
    `Note2` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
    `Note3` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
    `Note4` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
    `Note5` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
    `DateAdded` float DEFAULT '0',
    `DateModified` float DEFAULT '0',
    `IsValidable` bit(1) NOT NULL DEFAULT b'1',
    `Qty` int(11) DEFAULT '1',
    `upsize_ts` timestamp NULL DEFAULT NULL,
    `Note6` varchar(300) CHARACTER SET utf8 DEFAULT '',
    `Note7` varchar(300) CHARACTER SET utf8 DEFAULT '',
    `Note8` varchar(300) CHARACTER SET utf8 DEFAULT '',
    `Note9` varchar(300) CHARACTER SET utf8 DEFAULT '',
    `Note10` varchar(300) CHARACTER SET utf8 DEFAULT '',
    `PowerOutputId` = integer("MODEL_ID")
    `AssetLogicalName` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '',
`AssetItemRef` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
`AssetItemOrder` int(11) DEFAULT '0',
`AssetFileName` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
`TransmissionElecControlledID` = integer("MODEL_ID")
`TransmissionMfrID` = integer("MODEL_ID")*/
}

object PartToBaseVehicles : Table("PART_TO_BASE_VEHICLES"){
    val id = integer("PART_TO_BASE_VEHICLE_ID").primaryKey()
    val baseVehicle_id = integer("BASE_VEHICLE_ID").references(BaseVehicles.id, ReferenceOption.CASCADE)
    val part_id = integer("PART_ID").references(Parts.id, ReferenceOption.CASCADE)
    val position_id = integer("POSITION_ID").references(Positions.id, ReferenceOption.CASCADE)
}