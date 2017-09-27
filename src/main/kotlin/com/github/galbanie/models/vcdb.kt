package com.github.galbanie.models

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
 * Created by Galbanie on 2017-09-03.
 */

/*object BaseVehicles : Table("BaseVehicle") {
    val id = integer("BaseVehicleID").primaryKey()
    val Year_id = integer("YearID").references(Years.id, ReferenceOption.CASCADE)
    val Make_id = integer("MakeID").references(Makes.id, ReferenceOption.CASCADE)
    val Model_id = integer("ModelID").references(Models.id, ReferenceOption.CASCADE)
}*/

object BaseVehicles : IntIdTable("basevehicle","BaseVehicleID") {
    val year_id = reference("YearID",Years)
    val make_id = reference("MakeID", Makes)
    val model_id = reference("ModelID", Models)
}

class BaseVehicle(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BaseVehicle>(BaseVehicles)

    var year by Year referencedOn BaseVehicles.year_id
    var make by Make referencedOn BaseVehicles.make_id
    var model by Model referencedOn BaseVehicles.model_id
}

/*object Years : Table("Year"){
    val id = integer("YearID").primaryKey()
}*/

object Years : IntIdTable("year","YearID")

class Year(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Year>(Years)
}

/*object Makes : Table("Make"){
    val id = integer("MakeID").primaryKey()
    val name = varchar("MakeName",50)
}*/
object Makes : IntIdTable("make","MakeID"){
    val name = varchar("MakeName",50)
}
class Make(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Make>(Makes)
    var make by Makes.name
}

/*object VehicleTypes : Table("VehicleType"){
    val id = integer("VehicleTypeID").primaryKey()
    val name = varchar("VehicleTypeName",50)
    val VehicleTypeGroup_id = integer("VehicleTypeGroupID").references(VehicleTypeGroups.id, ReferenceOption.CASCADE)
}*/
object VehicleTypes : IntIdTable("vehicletype","VehicleTypeID"){
    val name = varchar("VehicleTypeName",50)
    val vehicleTypeGroup_id = reference("VehicleTypeGroupID", VehicleTypeGroups)
}
class VehicleType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleType>(VehicleTypes)
    var vehicleType by VehicleTypes.name
    var vehicleTypeGroup by VehicleTypeGroup referencedOn VehicleTypes.vehicleTypeGroup_id
}

/*object Models : Table("Model"){
    val id = integer("ModelID").primaryKey()
    val name = varchar ("ModelName",100)
    val vehicleType_id = integer("VehicleTypeID").references(VehicleTypes.id, ReferenceOption.CASCADE)
}*/
object Models : IntIdTable("model","ModelID"){
    val name = varchar ("ModelName",100)
    val vehicleType_id = reference("VehicleTypeID",VehicleTypes)
}
class Model(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Model>(Models)
    var model by Models.name
    var vehicleType by VehicleType referencedOn Models.vehicleType_id
}
/*object SubModels : Table("SubModel"){
    val id = integer("SubModelID").primaryKey()
    val name = varchar ("SubModelName",50)
}*/
object SubModels : IntIdTable("submodel","SubmodelID"){
    val name = varchar ("SubmodelName",50)
}
class SubModel(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SubModel>(SubModels)
    var subModel by SubModels.name
}
/*object VehicleTypeGroups : Table("VehicleTypeGroup"){
    val id = integer("VehicleTypeGroupID").primaryKey()
    val name = varchar("VehicleTypeGroupName",50)
}*/
object VehicleTypeGroups : IntIdTable("vehicletypegroup","VehicleTypeGroupID"){
    val name = varchar("VehicleTypeGroupName",50)
}
class VehicleTypeGroup(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleTypeGroup>(VehicleTypeGroups)
    var vehicleTypeGroup by VehicleTypeGroups.name
}
/*object MfrBodyCodes : Table("MfrBodyCode"){
    val id = integer("MfrBodyCodeID").primaryKey()
    val name = varchar ("MfrBodyCodeName",10)
}*/
object MfrBodyCodes : IntIdTable("mfrbodycode","MfrBodyCodeID"){
    val name = varchar ("MfrBodyCodeName",10)
}
class MfrBodyCode(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MfrBodyCode>(MfrBodyCodes)
    var mfrBodyCode by MfrBodyCodes.name
}
/*object BodyNumDoors : Table("BodyNumDoors"){
    val id = integer("BodyNumDoorsID").primaryKey()
    val numDoor = varchar ("BodyNumDoors",3)
}*/
object BodyNumDoors : IntIdTable("bodynumdoors","BodyNumDoorsID"){
    val numDoor = varchar ("BodyNumDoors",3)
}
class BodyNumDoor(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BodyNumDoor>(BodyNumDoors)
    var bodyNumDoor by BodyNumDoors.numDoor
}
/*object BodyTypes : Table("BodyType"){
    val id = integer("BodyTypeID").primaryKey()
    val name = varchar ("BodyTypeName",50)
}*/
object BodyTypes : IntIdTable("bodytype","BodyTypeID"){
    val name = varchar ("BodyTypeName",50)
}
class BodyType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BodyType>(BodyTypes)
    var bodyType by BodyTypes.name
}
/*object DriveTypes : Table("DriveType"){
    val id = integer("DriveTypeID").primaryKey()
    val name = varchar ("DriveTypeName",30)
}*/
object DriveTypes : IntIdTable("drivetype","DriveTypeID"){
    val name = varchar ("DriveTypeName",30)
}
class DriveType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DriveType>(DriveTypes)
    var driveType by DriveTypes.name
}
/*object EngineBases : Table("EngineBase"){
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
}*/
object EngineBases : IntIdTable("enginebase","EngineBaseID"){
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
class EngineBase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineBase>(EngineBases)
    var engineBaseLiter by EngineBases.liter
    var engineBaseCC by EngineBases.cc
    var engineBaseCid by EngineBases.cid
    var engineBaseCylinder by EngineBases.cylinders
    var engineBaseBlockType by EngineBases.blockType
    var engineBaseEngBoreIn by EngineBases.engBoreIn
    var engineBaseEngBoreMetric by EngineBases.engBoreMetric
    var engineBaseEngStrokeIn by EngineBases.engStrokeIn
    var engineBaseEngStrokeMetric by EngineBases.engStrokeMetric
}
/*object EngineDesignations : Table("EngineDesignation"){
    val id = integer("EngineDesignationID").primaryKey()
    val name = varchar ("EngineDesignationName",30)
}*/
object EngineDesignations : IntIdTable("enginedesignation","EngineDesignationID"){
    val name = varchar ("EngineDesignationName",30)
}
class EngineDesignation(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineDesignation>(EngineDesignations)
    var engineDesignation by EngineDesignations.name
}
/*object EngineMfrs : Table("EngineMfr"){
    val id = integer("EngineMfrID").primaryKey()
    val name = varchar ("EngineMfrName",50)
}*/
object EngineMfrs : IntIdTable("enginemfr","EngineMfrID"){
    val name = varchar ("EngineMfrName",50)
}
class EngineMfr(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineMfr>(EngineMfrs)
    var engineMfr by EngineMfrs.name
}
/*object FuelDeliveryConfigs : Table("FuelDeliveryConfig"){
    val id = integer("FuelDeliveryConfigID").primaryKey()
    val FuelDeliveryType_id = integer("FuelDeliveryTypeID").references(FuelDeliveryTypes.id, ReferenceOption.CASCADE)
    val FuelDeliverySubType_id = integer("FuelDeliverySubTypeID").references(FuelDeliverySubTypes.id, ReferenceOption.CASCADE)
    val FuelSystemControlType_id = integer("FuelSystemControlTypeID").references(FuelSystemControlTypes.id, ReferenceOption.CASCADE)
    val FuelSystemDesign_id = integer("FuelSystemDesignID").references(FuelSystemDesigns.id, ReferenceOption.CASCADE)
}*/
object FuelDeliveryConfigs : IntIdTable("fueldeliveryconfig","FuelDeliveryConfigID"){
    val fuelDeliveryType_id = reference("FuelDeliveryTypeID",FuelDeliveryTypes)
    val fuelDeliverySubType_id = reference("FuelDeliverySubTypeID",FuelDeliverySubTypes)
    val fuelSystemControlType_id = reference("FuelSystemControlTypeID",FuelSystemControlTypes)
    val fuelSystemDesign_id = reference("FuelSystemDesignID",FuelSystemDesigns)
}
class FuelDeliveryConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelDeliveryConfig>(FuelDeliveryConfigs)

    var fuelDeliveryType by FuelDeliveryType referencedOn FuelDeliveryConfigs.fuelDeliveryType_id
    var fuelDeliverySubType by FuelDeliverySubType referencedOn FuelDeliveryConfigs.fuelDeliverySubType_id
    var fuelSystemControlType by FuelSystemControlType referencedOn FuelDeliveryConfigs.fuelSystemControlType_id
    var fuelSystemDesign by FuelSystemDesign referencedOn FuelDeliveryConfigs.fuelSystemDesign_id
}
/*object FuelSystemDesigns : Table("FuelSystemDesign"){
    val id = integer("FuelSystemDesignID").primaryKey()
    val name = varchar ("FuelSystemDesignName",50)
}*/
object FuelSystemDesigns : IntIdTable("fuelsystemdesign","FuelSystemDesignID"){
    val name = varchar ("FuelSystemDesignName",50)
}
class FuelSystemDesign(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelSystemDesign>(FuelSystemDesigns)
    var fuelSystemDesign by FuelSystemDesigns.name
}
/*object FuelSystemControlTypes : Table("FuelSystemControlType"){
    val id = integer("FuelSystemControlTypeID").primaryKey()
    val name = varchar ("FuelSystemControlTypeName",50)
}*/
object FuelSystemControlTypes : IntIdTable("fuelsystemcontroltype","FuelSystemControlTypeID"){
    val name = varchar ("FuelSystemControlTypeName",50)
}
class FuelSystemControlType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelSystemControlType>(FuelSystemControlTypes)
    var fuelSystemControlType by FuelSystemControlTypes.name
}
/*object FuelDeliverySubTypes : Table("FuelDeliverySubType"){
    val id = integer("FuelDeliverySubTypeID").primaryKey()
    val name = varchar ("FuelDeliverySubTypeName",50)
}*/
object FuelDeliverySubTypes : IntIdTable("fueldeliverysubtype","FuelDeliverySubTypeID"){
    val name = varchar ("FuelDeliverySubTypeName",50)
}
class FuelDeliverySubType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelDeliverySubType>(FuelDeliverySubTypes)
    var fuelDeliverySubType by FuelDeliverySubTypes.name
}
/*object FuelDeliveryTypes : Table("FuelDeliveryType"){
    val id = integer("FuelDeliveryTypeID").primaryKey()
    val name = varchar ("FuelDeliveryTypeName",50)
}*/
object FuelDeliveryTypes : IntIdTable("fueldeliverytype","FuelDeliveryTypeID"){
    val name = varchar ("FuelDeliveryTypeName",50)
}
class FuelDeliveryType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelDeliveryType>(FuelDeliveryTypes)
    var fuelDeliveryType by FuelDeliveryTypes.name
}
/*object Aspirations : Table("Aspiration"){
    val id = integer("AspirationID").primaryKey()
    val name = varchar ("AspirationName",30)
}*/
object Aspirations : IntIdTable("aspiration","AspirationID"){
    val name = varchar ("AspirationName",30)
}
class Aspiration(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Aspiration>(Aspirations)
    var aspiration by Aspirations.name
}
/*object CylinderHeadTypes : Table("CylinderHeadType"){
    val id = integer("CylinderHeadTypeID").primaryKey()
    val name = varchar ("CylinderHeadTypeName",30)
}*/
object CylinderHeadTypes : IntIdTable("cylinderheadtype","CylinderHeadTypeID"){
    val name = varchar ("CylinderHeadTypeName",30)
}
class CylinderHeadType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CylinderHeadType>(CylinderHeadTypes)
    var cylinderHeadType by CylinderHeadTypes.name
}
/*object FuelTypes : Table("FuelType"){
    val id = integer("FuelTypeID").primaryKey()
    val name = varchar ("FuelTypeName",30)
}*/
object FuelTypes : IntIdTable("fueltype","FuelTypeID"){
    val name = varchar ("FuelTypeName",30)
}
class FuelType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelType>(FuelTypes)
    var fuelType by FuelTypes.name
}
/*object IgnitionSystemTypes : Table("IgnitionSystemType"){
    val id = integer("IgnitionSystemTypeID").primaryKey()
    val name = varchar ("IgnitionSystemTypeName",30)
}*/
object IgnitionSystemTypes : IntIdTable("IgnitionSystemType","IgnitionSystemTypeID"){
    val name = varchar ("IgnitionSystemTypeName",30)
}
class IgnitionSystemType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IgnitionSystemType>(IgnitionSystemTypes)
    var ignitionSystemType by IgnitionSystemTypes.name
}
/*object TransmissionTypes : Table("TransmissionType"){
    val id = integer("TransmissionTypeID").primaryKey()
    val name = varchar ("TransmissionTypeName",30)
}*/
object TransmissionTypes : IntIdTable("transmissiontype","TransmissionTypeID"){
    val name = varchar ("TransmissionTypeName",30)
}
class TransmissionType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionType>(TransmissionTypes)
    var transmissionType by TransmissionTypes.name
}
/*object TransmissionControlTypes : Table("TransmissionControlType"){
    val id = integer("TransmissionControlTypeID").primaryKey()
    val name = varchar ("TransmissionControlTypeName",30)
}*/
object TransmissionControlTypes : IntIdTable("transmissioncontroltype","TransmissionControlTypeID"){
    val name = varchar ("TransmissionControlTypeName",30)
}
class TransmissionControlType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionControlType>(TransmissionControlTypes)
    var transmissionControlType by TransmissionControlTypes.name
}
/*object TransmissionMfrCodes : Table("TransmissionMfrCode"){
    val id = integer("TransmissionMfrCodeID").primaryKey()
    val name = varchar ("TransmissionMfrCode",30)
}*/
object TransmissionMfrCodes : IntIdTable("transmissionmfrcode","TransmissionMfrCodeID"){
    val name = varchar ("TransmissionMfrCode",30)
}
class TransmissionMfrCode(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionMfrCode>(TransmissionMfrCodes)
    var transmissionMfrCode by TransmissionMfrCodes.name
}
/*object TransmissionNumSpeeds : Table("TransmissionNumSpeeds"){
    val id = integer("TransmissionNumSpeedsID").primaryKey()
    val numbSpeed = varchar("TransmissionNumSpeeds",3)
}*/
object TransmissionNumSpeeds : IntIdTable("transmissionnumspeeds"){
    val numbSpeed = varchar("TransmissionNumSpeeds",3)
}
class TransmissionNumSpeed(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionNumSpeed>(TransmissionNumSpeeds)
    var transmissionNumSpeed by TransmissionNumSpeeds.numbSpeed
}
/*object TransmissionBases : Table("TransmissionBase"){
    val id = integer("TransmissionBaseID").primaryKey()
    val TransmissionType_id = integer("TransmissionTypeID").references(TransmissionTypes.id, ReferenceOption.CASCADE)
    val TransmissionControlType_id = integer("TransmissionControlTypeID").references(TransmissionControlTypes.id, ReferenceOption.CASCADE)
    val TransmissionNumSpeed_id = integer("TransmissionNumSpeedsID").references(TransmissionNumSpeeds.id, ReferenceOption.CASCADE)
}*/
object TransmissionBases : IntIdTable("transmissionbase","TransmissionBaseID"){
    val transmissionType_id = reference("TransmissionTypeID",TransmissionTypes)
    val transmissionControlType_id = reference("TransmissionControlTypeID",TransmissionControlTypes)
    val transmissionNumSpeed_id = reference("TransmissionNumSpeedsID",TransmissionNumSpeeds)
}
class TransmissionBase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionBase>(TransmissionBases)

    var transmissionType by TransmissionType referencedOn TransmissionBases.transmissionType_id
    var transmissionControlType by TransmissionControlType referencedOn TransmissionBases.transmissionControlType_id
    var transmissionNumSpeed by TransmissionNumSpeed referencedOn TransmissionBases.transmissionNumSpeed_id

}
/*object BedLengths : Table("BedLength"){
    val id = integer("BedLengthID").primaryKey()
    val name = varchar ("BedLength",10)
    val metric = varchar("BedLengthMetric",10)
}*/
object BedLengths : IntIdTable("bedlength","BedLengthID"){
    val name = varchar ("BedLength",10)
    val metric = varchar("BedLengthMetric",10)
}
class BedLength(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BedLength>(BedLengths)
    var bedLengthName by BedLengths.name
    var bedMetric by BedLengths.metric
}
/*object BedTypes : Table("BedType"){
    val id = integer("BedTypeID").primaryKey()
    val name = varchar ("BedTypeName",50)
}*/
object BedTypes : IntIdTable("bedtype","BedTypeID"){
    val name = varchar ("BedTypeName",50)
}
class BedType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BedType>(BedTypes)
    var bedType by BedLengths.name
}
/*object BedConfigs : Table("BedConfig"){
    val id = integer("BedConfigID").primaryKey()
    val BedLength_id = integer("BedLengthID").references(BedLengths.id, ReferenceOption.CASCADE)
    val BedType_id = integer("BedTypeID").references(BedTypes.id, ReferenceOption.CASCADE)
}*/
object BedConfigs : IntIdTable("bedconfig","BedConfigID"){
    val bedLength_id = reference("BedLengthID",BedLengths)
    val bedType_id = reference("BedTypeID",BedTypes)
}
class BedConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BedConfig>(BedConfigs)

    var bedLength by BedLength referencedOn BedConfigs.bedLength_id
    var bedType by BedType referencedOn BedConfigs.bedType_id
}
/*object WheelBases : Table("WheelBase"){
    val id = integer("WheelBaseID").primaryKey()
    val name = varchar ("WheelBaseName",10)
    val metric = varchar("WheelBaseMetric",10)
}*/
object WheelBases : IntIdTable("wheelbase","WheelBaseID"){
    val name = varchar ("WheelBase",10)
    val metric = varchar("WheelBaseMetric",10)
}
class WheelBase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WheelBase>(WheelBases)
    var wheelBaseName by WheelBases.name
    var wheelBaseMetric by WheelBases.metric
}
/*object BrakeConfigs : Table("BrakeConfig"){
    val id = integer("BrakeConfigID").primaryKey()
    val FrontBrakeType_id = integer("FrontBrakeTypeID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val RearBrakeType_id = integer("RearBrakeTypeID").references(BrakeTypes.id, ReferenceOption.CASCADE)
    val BrakeSystem_id = integer("BrakeSystemID").references(BrakeSystems.id, ReferenceOption.CASCADE)
    val BrakeAbs_id = integer("BrakeABSID").references(BrakeAbs.id, ReferenceOption.CASCADE)
}*/
object BrakeConfigs : IntIdTable("brakeconfig","BrakeConfigID"){
    val frontBrakeType_id = reference("FrontBrakeTypeID",BrakeTypes)
    val rearBrakeType_id = reference("RearBrakeTypeID",BrakeTypes)
    val brakeSystem_id = reference("BrakeSystemID",BrakeSystems)
    val brakeAbs_id = reference("BrakeABSID", BrakeAbs)
}
class BrakeConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrakeConfig>(BrakeConfigs)

    var frontBrakeType by BrakeType referencedOn BrakeConfigs.frontBrakeType_id
    var rearBrakeType by BrakeType referencedOn BrakeConfigs.rearBrakeType_id
    var brakeSystem by BrakeSystem referencedOn BrakeConfigs.brakeSystem_id
    var brakeAbs by BrakeABS referencedOn BrakeConfigs.brakeAbs_id
}
/*object BrakeSystems : Table("BrakeSystem"){
    val id = integer("BrakeSystemID").primaryKey()
    val name = varchar("BrakeSystemName",30)
}*/
object BrakeSystems : IntIdTable("brakesystem","BrakeSystemID" ){
    val name = varchar("BrakeSystemName",30)
}
class BrakeSystem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrakeSystem>(BrakeSystems)
    var brakeSystem by BrakeSystems.name
}
/*object BrakeTypes : Table("BrakeType"){
    val id = integer("BrakeTypeID").primaryKey()
    val name = varchar("BrakeTypeName",30)
}*/

object BrakeTypes : IntIdTable("braketype","BrakeTypeID"){
    val name = varchar("BrakeTypeName",30)
}
class BrakeType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrakeType>(BrakeTypes)
    var brakeType by BrakeTypes.name
}
/*object BrakeAbs : Table("BrakeABS"){
    val id = integer("BrakeABSID").primaryKey()
    val name = varchar("BrakeABSName",30)
}*/
object BrakeAbs : IntIdTable("brakeabs", "BrakeABSID"){
    val name = varchar("BrakeABSName",30)
}
class BrakeABS(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrakeABS>(BrakeAbs)
    var brakeAbs by BrakeAbs.name
}
/*object SpringTypes : Table("SpringType"){
    val id = integer("SpringTypeID").primaryKey()
    val name = varchar("SpringTypeName",50)
}*/
object SpringTypes : IntIdTable("springtype","SpringTypeID"){
    val name = varchar("SpringTypeName",50)
}
class SpringType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SpringType>(SpringTypes)
    var springType by SpringTypes.name
}
/*object SpringTypeConfigs : Table("SpringTypeConfig"){
    val id = integer("SpringTypeConfigID").primaryKey()
    val FrontSpringType_id = integer("FrontSpringTypeID").references(SpringTypes.id, ReferenceOption.CASCADE)
    val RearSpringType_id = integer("RearSpringTypeID").references(SpringTypes.id, ReferenceOption.CASCADE)
}*/
object SpringTypeConfigs : IntIdTable("springtypeconfig","SpringTypeConfigID"){
    val frontSpringType_id = reference("FrontSpringTypeID",SpringTypes)
    val rearSpringType_id = reference("RearSpringTypeID",SpringTypes)
}

class SpringTypeConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SpringTypeConfig>(SpringTypeConfigs)
    var frontSpringType by SpringType referencedOn SpringTypeConfigs.frontSpringType_id
    var rearSpringType by SpringType referencedOn SpringTypeConfigs.rearSpringType_id
}

/*object SteeringTypes : Table("SteeringType"){
    val id = integer("SteeringTypeID").primaryKey()
    val name = varchar("SteeringTypeName",30)
}*/
object SteeringTypes : IntIdTable("steeringtype","SteeringTypeID"){
    val name = varchar("SteeringTypeName",30)
}
class SteeringType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SteeringType>(SteeringTypes)
    var steeringType by SteeringTypes.name
}
/*object SteeringSystems : Table("SteeringSystem"){
    val id = integer("SteeringSystemID").primaryKey()
    val name = varchar("SteeringSystemName",30)
}*/
object SteeringSystems : IntIdTable("steeringsystem","SteeringSystemID"){
    val name = varchar("SteeringSystemName",30)
}
class SteeringSystem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SteeringSystem>(SteeringSystems)
    var steeringSystem by SteeringSystems.name
}
/*object Regions : Table("Region"){
    val id = integer("RegionID").primaryKey()
    val parentId = integer("ParentID").references(Regions.id, ReferenceOption.CASCADE)
    val name = varchar("RegionName",30)
    val nameAbbr = varchar("RegionAbbr",3)
}*/
object Regions : IntIdTable("region","RegionID"){
    val parentId = reference("ParentID",Regions)
    val name = varchar("RegionName",30)
    val nameAbbr = varchar("RegionAbbr",3)
}
class Region(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Region>(Regions)
    var regionName by Regions.name
    var regionNameAbbr by Regions.nameAbbr
    var parent_id by Region referencedOn Regions.parentId
}
/*object EngineVersions: Table("EngineVersion"){
    val id = integer("EngineVersionID").primaryKey()
    val version = varchar("EngineVersion",20)
}*/
object EngineVersions: IntIdTable("engineversion","EngineVersionID" ){
    val version = varchar("EngineVersion",20)
}
class EngineVersion(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineVersion>(EngineVersions)
    var engineVersion by EngineVersions.version

}
/* Object non existant dans la vcdb mais existe dans le diagramme de classe
object EngineValves: Table("ENGINE_VALVES"){
    val id = integer("ENGINE_VALVE_ID").primaryKey()
    val numbValve = integer("ENGINE_VALVE_NUMBER")
}*/

/*object PowerOutputs: Table("PowerOutput"){
    val id = integer("PowerOutputID").primaryKey()
    val horsePower = varchar("HorsePower",10)
    val kilowattPower = varchar("KilowattPower",10)
}*/
object PowerOutputs: IntIdTable("poweroutput","PowerOutputID"){
    val horsePower = varchar("HorsePower",10)
    val kilowattPower = varchar("KilowattPower",10)
}
class PowerOutput(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PowerOutput>(PowerOutputs)
    var horsePower by PowerOutputs.horsePower
    var kilowattPower by PowerOutputs.kilowattPower

}
/*object PublicationStages: Table("PublicationStage"){
    val id = integer("PublicationStageID").primaryKey()
    val name = varchar("PublicationStageName",100)
}*/
object PublicationStages: IntIdTable("publicationstage","PublicationStageID"){
    val name = varchar("PublicationStageName",100)
}
class PublicationStage(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PublicationStage>(PublicationStages)
    var publicationStageName by PublicationStages.name
}
/*object VehicleToBedConfigs: Table("VehicleToBedConfig"){
    val id = integer("VehicleToBedConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val BedConfig_id = integer("BedConfigID").references(BedConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToBedConfigs: IntIdTable("vehicletobedconfig","VehicleToBedConfigID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val bedConfig_id = reference("BedConfigID", BedConfigs)
    val source_name = varchar("Source",10)
}
class VehicleToBedConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToBedConfig>(VehicleToBedConfigs)
    var vehicle by Vehicle referencedOn VehicleToBedConfigs.vehicle_id
    var bedConfig by BedConfig referencedOn VehicleToBedConfigs.bedConfig_id
    var source by VehicleToBedConfigs.source_name
}
/*object VehicleToMfrBodyCodes: Table("VehicleToMfrBodyCode"){
    val id = integer("VehicleToMfrBodyCodeID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val Mfr_id = integer("MfrBodyCodeID").references(MfrBodyCodes.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToMfrBodyCodes: IntIdTable("vehicletomfrbodycode","VehicleToMfrBodyCodeID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val mfr_id = reference("MfrBodyCodeID",MfrBodyCodes)
    val source_name = varchar("Source",10)
}
class VehicleToMfrBodyCode(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToMfrBodyCode>(VehicleToMfrBodyCodes)
    var vehicle by Vehicle referencedOn VehicleToMfrBodyCodes.vehicle_id
    var mfr by MfrBodyCode referencedOn VehicleToMfrBodyCodes.mfr_id
    var source by VehicleToMfrBodyCodes.source_name
}
/*object VehicleToBodyStyleConfigs: Table("VehicleToBodyStyleConfig"){
    val id = integer("VehicleToBodyStyleConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val BodyStyleConfig_id = integer("BodyStyleConfigID").references(BodyStyleConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToBodyStyleConfigs: IntIdTable("vehicletobodystyleconfig","VehicleToBodyStyleConfigID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val bodyStyleConfig_id = reference("BodyStyleConfigID",BodyStyleConfigs)
    val source_name = varchar("Source",10)
}
class VehicleToBodyStyleConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToBodyStyleConfig>(VehicleToBodyStyleConfigs)
    var vehicle by Vehicle referencedOn VehicleToBodyStyleConfigs.vehicle_id
    var bodyStyleConfig by BodyStyleConfig referencedOn VehicleToBodyStyleConfigs.bodyStyleConfig_id
    var source by VehicleToBodyStyleConfigs.source_name
}
/*object VehicleToSpringTypeConfigs: Table("VehicleToSpringTypeConfig"){
    val id = integer("VehicleToSpringTypeConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val SpringTypeConfig_id = integer("SpringTypeConfigID").references(SpringTypeConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToSpringTypeConfigs: IntIdTable("vehicletospringtypeconfig","VehicleToSpringTypeConfigID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val springTypeConfig_id = reference("SpringTypeConfigID",SpringTypeConfigs)
    val source_name = varchar("Source",10)
}
class VehicleToSpringTypeConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToSpringTypeConfig>(VehicleToSpringTypeConfigs)
    var vehicle by Vehicle referencedOn VehicleToSpringTypeConfigs.vehicle_id
    var springTypeConfig by SpringTypeConfig referencedOn VehicleToSpringTypeConfigs.springTypeConfig_id
    var source by VehicleToSpringTypeConfigs.source_name
}
/*object VehicleToBrakeConfigs: Table("VehicleToBrakeConfig"){
    val id = integer("VehicleToBrakeConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val BrakeConfig_id = integer("BrakeConfigID").references(BrakeConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToBrakeConfigs: IntIdTable("vehicletobrakeconfig","VehicleToBrakeConfigID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val brakeConfig_id = reference("BrakeConfigID",BrakeConfigs)
    val source_name = varchar("Source",10)
}
class VehicleToBrakeConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToBrakeConfig>(VehicleToBrakeConfigs)
    var vehicle by Vehicle referencedOn VehicleToBrakeConfigs.vehicle_id
    var brakeConfig by BrakeConfig referencedOn VehicleToBrakeConfigs.brakeConfig_id
    var source by VehicleToBrakeConfigs.source_name
}
/*object VehicleToSteeringConfigs: Table("VehicleToSteeringConfig"){
    val id = integer("VehicleToSteeringConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val SteeringConfig_id = integer("SteeringConfigID").references(SteeringConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToSteeringConfigs: IntIdTable("vehicletosteeringconfig","VehicleToSteeringConfigID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val steeringConfig_id = reference("SteeringConfigID",SteeringConfigs)
    val source_name = varchar("Source",10)
}
class VehicleToSteeringConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToSteeringConfig>(VehicleToSteeringConfigs)
    var vehicle by Vehicle referencedOn VehicleToSteeringConfigs.vehicle_id
    var steeringConfig by SteeringConfig referencedOn VehicleToSteeringConfigs.steeringConfig_id
    var source by VehicleToSteeringConfigs.source_name
}
/*object VehicleToDriveTypes: Table("VehicleToDriveType"){
    val id = integer("VehicleToDriveTypeID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val DriveType_id = integer("DriveTypeID").references(DriveTypes.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToDriveTypes: IntIdTable("vehicletodrivetype","VehicleToDriveTypeID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val driveType_id = reference("DriveTypeID",DriveTypes)
    val source_name = varchar("Source",10)
}
class VehicleToDriveType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToDriveType>(VehicleToDriveTypes)
    var vehicle by Vehicle referencedOn VehicleToDriveTypes.vehicle_id
    var driveType by DriveType referencedOn VehicleToDriveTypes.driveType_id
    var source by VehicleToDriveTypes.source_name
}
/*object VehicleToTransmissions: Table("VehicleToTransmission"){
    val id = integer("VehicleToTransmissionID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val Transmission_id = integer("TransmissionID").references(Transmissions.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToTransmissions: IntIdTable("vehicletotransmission","VehicleToTransmissionID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val transmission_id = reference("TransmissionID",Transmissions)
    val source_name = varchar("Source",10)
}
class VehicleToTransmission(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToTransmission>(VehicleToTransmissions)
    var vehicle by Vehicle referencedOn VehicleToTransmissions.vehicle_id
    var transmission by Transmission referencedOn VehicleToTransmissions.transmission_id
    var source by VehicleToTransmissions.source_name
}
/*object VehicleToEngineConfigs: Table("VehicleToEngineConfig"){
    val id = integer("VehicleToEngineConfigID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val EngineConfig_id = integer("EngineConfigID").references(EngineConfigs.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",10)
}*/
object VehicleToEngineConfigs: IntIdTable("vehicletoengineconfig","VehicleToEngineConfigID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val engineConfig_id = reference("EngineConfigID",EngineConfigs)
    val source_name = varchar("Source",10)
}
class VehicleToEngineConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToEngineConfig>(VehicleToEngineConfigs)
    var vehicle by Vehicle referencedOn VehicleToEngineConfigs.vehicle_id
    var engineConfig by EngineConfig referencedOn VehicleToEngineConfigs.engineConfig_id
    var source by VehicleToEngineConfigs.source_name
}
/*object VehicleToWheelbases: Table("VehicleToWheelbase"){
    val id = integer("VehicleToWheelbaseID").primaryKey()
    val Vehicle_id = integer("VehicleID").references(Vehicles.id, ReferenceOption.CASCADE)
    val Wheelbase_id = integer("WheelbaseID").references(WheelBases.id, ReferenceOption.CASCADE)
    val source_name = varchar("Source",255)
}*/
// Probleme voir table
object VehicleToWheelbases: IntIdTable("vehicletowheelbase","VehicleToWheelbaseID"){
    val vehicle_id = reference("VehicleID",Vehicles)
    val wheelbase_id = reference("WheelBaseID",WheelBases)
    val source_name = varchar("Source",255)
}
class VehicleToWheelbase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleToWheelbase>(VehicleToWheelbases)
    var vehicle by Vehicle referencedOn VehicleToWheelbases.vehicle_id
    var wheelbase by WheelBase referencedOn VehicleToWheelbases.wheelbase_id
    var source by VehicleToWheelbases.source_name
}
/*object Vehicles: Table("Vehicle"){
    val id = integer("VehicleID").primaryKey()
    val BaseVehicle_id = integer("BaseVehicleID").references(BaseVehicles.id, ReferenceOption.CASCADE)
    val SubModel_id = integer("SubModelID").references(SubModels.id, ReferenceOption.CASCADE)
    val Region_id = integer("RegionID").references(Regions.id, ReferenceOption.CASCADE)
    val PublicationStage_id = integer("PublicationStageID").references(PublicationStages.id, ReferenceOption.CASCADE)
    val Publication_name = varchar("PublicationStageSource",100)
    val Publication_date = date("PublicationStageDate")
    val source_name = varchar("Source",10)
    val PublicationStageDate = date("PublicationStageDate")
}*/
object Vehicles: IntIdTable("vehicle","VehicleID"){
    val baseVehicle_id = reference("BaseVehicleID",BaseVehicles)
    val subModel_id = reference("SubModelID",SubModels)
    val region_id = reference("RegionID",Regions)
    val publicationStage_id = reference("PublicationStageID",PublicationStages)
    val publication_name = varchar("PublicationStageSource",100)
    val publication_date = date("PublicationStageDate")
    val source_name = varchar("Source",10)
    val publicationStageDate = date("PublicationStageDate")
}
class Vehicle(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Vehicle>(Vehicles)
    var baseVehicle by BaseVehicle referencedOn Vehicles.baseVehicle_id
    var subModel by SubModel referencedOn Vehicles.subModel_id
    var region by Region referencedOn Vehicles.region_id
    var publicationStage by PublicationStage referencedOn Vehicles.publicationStage_id
    var publicationName by Vehicles.publication_name
    var publicationDate by Vehicles.publication_date
    var source by Vehicles.source_name
    var publicationStageDate by Vehicles.publicationStageDate
}
/*object BodyStyleConfigs: Table("BodyStyleConfig"){
    val id = integer("BodyStyleConfigID").primaryKey()
    val BodyNumDoors_id = integer("BodyNumDoorsID").references(BodyNumDoors.id, ReferenceOption.CASCADE)
    val BodyType_id = integer("BodyTypeID").references(BodyTypes.id, ReferenceOption.CASCADE)
}*/
object BodyStyleConfigs: IntIdTable("bodystyleconfig","BodyStyleConfigID"){
    val bodyNumDoors_id = reference("BodyNumDoorsID",BodyNumDoors)
    val bodyType_id = reference("BodyTypeID",BodyTypes)
}
class BodyStyleConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BodyStyleConfig>(BodyStyleConfigs)
    var bodyNumDoors by BodyNumDoor referencedOn BodyStyleConfigs.bodyNumDoors_id
    var bodyType by BodyType referencedOn BodyStyleConfigs.bodyType_id

}
/*object SteeringConfigs: Table("SteeringConfig"){
    val id = integer("SteeringConfigID").primaryKey()
    val SteeringType_id = integer("SteeringTypeID").references(SteeringTypes.id, ReferenceOption.CASCADE)
    val SteeringSystem_id = integer("SteeringSystemID").references(SteeringSystems.id, ReferenceOption.CASCADE)
}*/
object SteeringConfigs: IntIdTable("steeringconfig","SteeringConfigID"){
    val steeringType_id = reference("SteeringTypeID",SteeringTypes)
    val steeringSystem_id = reference("SteeringSystemID",SteeringSystems)
}
class SteeringConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SteeringConfig>(SteeringConfigs)
    var steeringType by SteeringType referencedOn SteeringConfigs.steeringType_id
    var steeringSystem by SteeringSystem referencedOn SteeringConfigs.steeringSystem_id

}
/*object Valves: Table("Valves"){
    val id = integer("ValvesID").primaryKey()
    val valvesPerEngine = varchar("ValvesPerEngine",3)
}*/
object Valves: IntIdTable("valves","ValvesID"){
    val valvesPerEngine = varchar("ValvesPerEngine",3)
}
class Valve(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Valve>(Valves)
    var valvesPerEngine by Valves.valvesPerEngine
}
/*object Mfrs: Table("Mfr"){
    val id = integer("MfrID").primaryKey()
    val name = varchar("MfrName",30)
}*/
object Mfrs: IntIdTable("mfr","MfrID"){
    val name = varchar("MfrName",30)
}
class Mfr(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Mfr>(Mfrs)
    var name by Mfrs.name
}
/*object EngineVINs: Table("EngineVIN"){
    val id = integer("EngineVINID").primaryKey()
    val name = varchar("EngineVINName",5)
}*/
object EngineVINs: IntIdTable("enginevin","EngineVINID"){
    val name = varchar("EngineVINName",5)
}
class EngineVIN(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineVIN>(EngineVINs)
    var name by EngineVINs.name
}
/*object EngineConfigs: Table("EngineConfig"){
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
    val PowerOutputId = integer("PowerOutputID")
}*/
// mm chose ici pour poweroutputs
object EngineConfigs: IntIdTable("engineconfig","EngineConfigID"){
    val engineDesignation_id = reference("EngineDesignationID",EngineDesignations)
    val engineVIN_id = reference("EngineVINID",EngineVINs)
    val valve_id = reference("ValvesID",Valves)
    val engineBase_id = reference("EngineBaseID",EngineBases)
    val fuelDeliveryConfig_id = reference("FuelDeliveryConfigID",FuelDeliveryConfigs)
    val aspiration_id = reference("AspirationID", Aspirations)
    val cylinderHeadType_id = reference("CylinderHeadTypeID",CylinderHeadTypes)
    val fuelType_id = reference("FuelTypeID",FuelTypes)
    val ignitionSystemType_id = reference("IgnitionSystemTypeID",IgnitionSystemTypes)
    val engineMfr_id = reference("EngineMfrID",EngineMfrs)
    val engineVersion_id = reference("EngineVersionID",EngineVersions)
    val powerOutput_id = reference("PowerOutputID",PowerOutputs)
}
class EngineConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineConfig>(EngineConfigs)
    var engineDesignation by EngineDesignation referencedOn EngineConfigs.engineDesignation_id
    var engineVIN by EngineVIN referencedOn EngineConfigs.engineVIN_id
    var valve by Valve referencedOn EngineConfigs.valve_id
    var engineBase by EngineBase referencedOn EngineConfigs.engineBase_id
    var fuelDeliveryConfig by FuelDeliveryConfig referencedOn EngineConfigs.fuelDeliveryConfig_id
    var aspiration by Aspiration referencedOn EngineConfigs.aspiration_id
    var cylinderHeadType by CylinderHeadType referencedOn EngineConfigs.cylinderHeadType_id
    var fuelType by FuelType referencedOn EngineConfigs.fuelType_id
    var ignitionSystemType by IgnitionSystemType referencedOn EngineConfigs.ignitionSystemType_id
    var engineMfr by EngineMfr referencedOn EngineConfigs.engineMfr_id
    var engineVersion by EngineVersion referencedOn EngineConfigs.engineVersion_id
    var powerOutput by PowerOutput referencedOn EngineConfigs.powerOutput_id
}
/*object ElecControlleds: Table("ElecControlled"){
    val id = integer("ElecControlledID").primaryKey()
    val name = varchar("ElecControlled",3)
}*/
object ElecControlleds: IntIdTable("eleccontrolled","ElecControlledID"){
    val name = varchar("ElecControlled",3)
}
class ElecControlled(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ElecControlled>(ElecControlleds)
    var name by ElecControlleds.name
}
/*object TransmissionMfrs: Table("TransmissionMfr"){
    val id = integer("TransmissionMfrID").primaryKey()
    val name = varchar("TransmissionMfrName",255)
}*/
object TransmissionMfrs: IntIdTable("transmissionmfrcode","TransmissionMfrID"){
    val name = varchar("TransmissionMfrName",255)
}
class TransmissionMfr(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionMfr>(TransmissionMfrs)
    var name by TransmissionMfrs.name
}
object Transmissions: IntIdTable("Transmission","TransmissionID"){
    val transmissionBase_id = reference("TransmissionBaseID",TransmissionBases)
    val transmissionMfrCode_id = reference("TransmissionMfrCodeID",TransmissionMfrCodes)
    val transmissionElecControlled_id = reference("TransmissionElecControlledID",ElecControlleds)
    val transmissionMfr_id = reference("TransmissionMfrID",TransmissionMfrs)
}
class Transmission(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Transmission>(Transmissions)
    var transmissionBase by TransmissionBase referencedOn Transmissions.transmissionBase_id
    var transmissionMfrCode by TransmissionMfrCode referencedOn Transmissions.transmissionMfrCode_id
    var transmissionElecControlled by ElecControlled referencedOn Transmissions.transmissionElecControlled_id
    var transmissionMfr by TransmissionMfr referencedOn Transmissions.transmissionMfr_id

}
/*object VCDBChanges: Table("VCDBChanges"){
    val id = integer("id").primaryKey()
    val versionDate = date("versiondate")
    val name = varchar("tablename",30)
    val action = varchar("action",1)
}*/
object VCDBChanges: IntIdTable("vcdbchanges","id"){
    val versionDate = date("versiondate")
    val name = varchar("tablename",30)
    val action = varchar("action",1)
}
class VCDBChange(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VCDBChange>(VCDBChanges)
    var name by VCDBChanges.name
    var versionDate by VCDBChanges.versionDate
    var action by VCDBChanges.action
}
/*object Versions: Table("Version"){
    val versionDate = date("VersionDate")
}*/
object Versions: IntIdTable("version","id"){
    val versionDate = date("VersionDate")
}
class Version(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Version>(Versions)
    var versionDate by Versions.versionDate
}
/*object Changes: Table("Changes"){
    val id = integer("ChangeID").primaryKey()
    val Request_id = integer("RequestID")
    val versionDate = date("RevDate")
    val ChangeReason_id = integer("ChangeReasonID").references(ChangeReasons.id, ReferenceOption.CASCADE)
}*/
object Changes: IntIdTable("changes","ChangeID"){
    val request_id = integer("RequestID")
    val versionDate = date("RevDate")
    val changeReason_id = reference("ChangeReasonID",ChangeReasons)
}
class Change(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Change>(Changes)
    var request by Changes.request_id
    var versionDate by Changes.versionDate
    var changeReason by ChangeReason referencedOn Changes.changeReason_id
}
/*object ChangeReasons: Table("ChangeReasons"){
    val id = integer("ChangeReasonID").primaryKey()
    val name = varchar("ChangeReason",255)
}*/
object ChangeReasons: IntIdTable("changereasons","ChangeReasonID"){
    val name = varchar("ChangeReason",255)
}
class ChangeReason(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChangeReason>(ChangeReasons)
    var name by ChangeReasons.name
}
/*object ChangeAttributeStates: Table("ChangeAttributeStates"){
    val id = integer("ChangeAttributeStateID").primaryKey()
    val name = varchar("ChangeAttributeState",255)
}*/
object ChangeAttributeStates: IntIdTable("changeattributestates","ChangeAttributeStateID"){
    val name = varchar("ChangeAttributeState",255)
}
class ChangeAttributeState(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChangeAttributeState>(ChangeAttributeStates)
    var name by ChangeAttributeStates.name
}
/*object ChangeTableNames: Table("ChangeTableNames"){
    val id = integer("TableNameID").primaryKey()
    val name = varchar("TableName",255)
    val name_Description = varchar("TableDescription",255)
}*/
object ChangeTableNames: IntIdTable("changetablenames","TableNameID"){
    val name = varchar("TableName",255)
    val name_Description = varchar("TableDescription",255)
}
class ChangeTableName(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChangeTableName>(ChangeTableNames)
    var name by ChangeTableNames.name
    var name_Desc by ChangeTableNames.name_Description
}
/*object ChangeDetails: Table("ChangeDetails"){
    val id = integer("ChangeDetailID").primaryKey()
    val Change_id = integer("ChangeID").references(Changes.id, ReferenceOption.CASCADE)
    val ChangeAttributeState_id = integer("ChangeAttributeStateID").references(ChangeAttributeStates.id, ReferenceOption.CASCADE)
    val TableName_id = integer("TableNameID").references(ChangeTableNames.id, ReferenceOption.CASCADE)
    val PrimaryKeyColumnName = varchar("PrimaryKeyColumnName",255)
    val PrimaryKeyBefore = integer("PrimaryKeyBefore")
    val PrimaryKeyAfter = integer("PrimaryKeyAfter")
    val ColumnName = varchar("ColumnName",255)
    val ColumnValueBefore = varchar("ColumnValueBefore",1000)
    val ColumnValueAfter = varchar("ColumnValueAfter",1000)
}*/
object ChangeDetails: IntIdTable("changedetails","ChangeDetailID"){
    val change_id = reference("ChangeID",Changes)
    val changeAttributeState_id = reference("ChangeAttributeStateID",ChangeAttributeStates)
    val tableName_id = reference("TableNameID",ChangeTableNames)
    val primaryKeyColumnName = varchar("PrimaryKeyColumnName",255)
    val primaryKeyBefore = integer("PrimaryKeyBefore")
    val primaryKeyAfter = integer("PrimaryKeyAfter")
    val columnName = varchar("ColumnName",255)
    val columnValueBefore = varchar("ColumnValueBefore",1000)
    val columnValueAfter = varchar("ColumnValueAfter",1000)
}
class ChangeDetail(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChangeDetail>(ChangeDetails)
    var change by Change referencedOn ChangeDetails.change_id
    var changeAttributeState by ChangeAttributeState referencedOn ChangeDetails.changeAttributeState_id
    var tableName by ChangeTableName referencedOn ChangeDetails.tableName_id
    var primaryKeyColumnName by ChangeDetails.primaryKeyColumnName
    var primaryKeyBefore by ChangeDetails.primaryKeyBefore
    var primaryKeyAfter by ChangeDetails.primaryKeyAfter
    var columnName by ChangeDetails.columnName
    var columnValueBefore by ChangeDetails.columnValueBefore
    var columnValueAfter by ChangeDetails.columnValueAfter
}
/*object Abbreviations: Table("Abbreviation"){
    val id = integer("Abbreviation").primaryKey()
    val name_Description = varchar("Description",20)
    val name_LongDescription = varchar("LongDescription",200)
}*/
object Abbreviations: IntIdTable("abbreviation","Abbreviation"){
    val name_Description = varchar("Description",20)
    val name_LongDescription = varchar("LongDescription",200)
}
class Abbreviation(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Abbreviation>(Abbreviations)
    var name_Desc by Abbreviations.name_Description
    var name_LongDesc by Abbreviations.name_LongDescription
}
/*object EnglishPhrases: Table("EnglishPhrase"){
    val id = integer("EnglishPhraseID").primaryKey()
    val EnglishPhrase = varchar("EnglishPhrase",100)
}*/
object EnglishPhrases: IntIdTable("englishphrase","EnglishPhraseID"){
    val englishPhrase = varchar("EnglishPhrase",100)
}
class EnglishPhrase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EnglishPhrase>(EnglishPhrases)
    var englishPhrase by EnglishPhrases.englishPhrase
}
/*object Languages: Table("Language"){
    val id = integer("LanguageID").primaryKey()
    val Language_name = varchar("LanguageName",20)
    val DialectName = varchar("DialectName",20)
}*/
object Languages: IntIdTable("language","LanguageID" ){
    val language_name = varchar("LanguageName",20)
    val dialectName = varchar("DialectName",20)
}
class Language(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Language>(Languages)
    var language_name by Languages.language_name
    var dialectName by Languages.dialectName
}
/*object LanguageTranslations: Table("LanguageTranslation"){
    val id = integer("LanguageTranslationID").primaryKey()
    val name = varchar("Translation",150)
    val EnglishPhrase_id = integer("EnglishPhraseID").references(EnglishPhrases.id, ReferenceOption.CASCADE)
    val Language_id = integer("LanguageID").references(Languages.id, ReferenceOption.CASCADE)
}*/
object LanguageTranslations: IntIdTable("languagetranslation","LanguageTranslationID"){
    val name = varchar("Translation",150)
    val englishPhrase_id = reference("EnglishPhraseID",EnglishPhrases)
    val language_id = reference("LanguageID",Languages)
}
class LanguageTranslation(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LanguageTranslation>(LanguageTranslations)
    var name by LanguageTranslations.name
    var englishPhrase by LanguageTranslations.englishPhrase_id
    var language by LanguageTranslations.language_id
}
/*object LanguageTranslationAttachments: Table("LanguageTranslationAttachment"){
    val id = integer("LanguageTranslationAttachmentID").primaryKey()
    val LanguageTranslation_id = integer("LanguageTranslationID").references(LanguageTranslations.id, ReferenceOption.CASCADE)
    val Attachment_id = integer("AttachmentID").references(Attachments.id, ReferenceOption.CASCADE)
}*/
object LanguageTranslationAttachments: IntIdTable("languagetranslationattachment","LanguageTranslationAttachmentID"){
    val languageTranslation_id = reference("LanguageTranslationID",LanguageTranslations)
    val attachment_id = reference("AttachmentID",Attachments)
}
class LanguageTranslationAttachment(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LanguageTranslationAttachment>(LanguageTranslationAttachments)
    var languageTranslation by LanguageTranslationAttachments.languageTranslation_id
    var attachment by LanguageTranslationAttachments.attachment_id
}
/*object Attachments: Table("Attachment"){
    val id = integer("AttachmentID").primaryKey()
    val AttachmentType_id = integer("AttachmentTypeID").references(AttachmentTypes.id, ReferenceOption.CASCADE)
    val AttachmentFileName = varchar("AttachmentFileName",50)
    val AttachmentURL = varchar("AttachmentURL",100)
    val AttachmentDescription = varchar("AttachmentDescription",50)
}*/
object Attachments: IntIdTable("attachment","AttachmentID"){
    val attachmentType_id = reference("AttachmentTypeID",AttachmentTypes)
    val attachmentFileName = varchar("AttachmentFileName",50)
    val attachmentURL = varchar("AttachmentURL",100)
    val attachmentDescription = varchar("AttachmentDescription",50)
}
class Attachment(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Attachment>(Attachments)
    var attachmentType by AttachmentType referencedOn Attachments.attachmentType_id
    var attachmentFileName by Attachments.attachmentFileName
    var attachmentURL by Attachments.attachmentURL
    var attachmentDescription by Attachments.attachmentDescription
}
/*object AttachmentTypes: Table("AttachmentType"){
    val id = integer("AttachmentTypeID").primaryKey()
    val name = varchar("AttachmentTypeName",20)
}*/
object AttachmentTypes: IntIdTable("attachmenttype","AttachmentTypeID"){
    val name = varchar("AttachmentTypeName",20)
}
class AttachmentType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AttachmentType>(AttachmentTypes)
    var name by AttachmentTypes.name
}
//https://github.com/JetBrains/Exposed

fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://localhost:3306/vcdb", driver = "com.mysql.jdbc.Driver",user = "root",password = "")
    transaction {
        Versions.selectAll().forEach {
            println(it[Versions.versionDate])
        }

        println("BaseVehicle: ${BaseVehicle.findById(1)?.year?.id}")

        println("Year: ${Year.findById(2009)?.id}")
        println("DriveType: ${DriveType.findById(1)?.id}")
    }
}