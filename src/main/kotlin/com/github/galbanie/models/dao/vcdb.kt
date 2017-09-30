package com.github.galbanie.models.dao.vcdb

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Created by Galbanie on 2017-09-30.
 */

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

object Years : IntIdTable("year","YearID")

class Year(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Year>(Years)
}

object Makes : IntIdTable("make","MakeID"){
    val name = varchar("MakeName",50)
}

class Make(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Make>(Makes)
    var make by Makes.name
}

object VehicleTypes : IntIdTable("vehicletype","VehicleTypeID"){
    val name = varchar("VehicleTypeName",50)
    val vehicleTypeGroup_id = reference("VehicleTypeGroupID", VehicleTypeGroups)
}
class VehicleType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleType>(VehicleTypes)
    var vehicleType by VehicleTypes.name
    var vehicleTypeGroup by VehicleTypeGroup referencedOn VehicleTypes.vehicleTypeGroup_id
}

object Models : IntIdTable("model","ModelID"){
    val name = varchar ("ModelName",100)
    val vehicleType_id = reference("VehicleTypeID",VehicleTypes)
}

class Model(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Model>(Models)
    var model by Models.name
    var vehicleType by VehicleType referencedOn Models.vehicleType_id
}

object SubModels : IntIdTable("submodel","SubmodelID"){
    val name = varchar ("SubmodelName",50)
}

class SubModel(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SubModel>(SubModels)
    var subModel by SubModels.name
}

object VehicleTypeGroups : IntIdTable("vehicletypegroup","VehicleTypeGroupID"){
    val name = varchar("VehicleTypeGroupName",50)
}

class VehicleTypeGroup(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VehicleTypeGroup>(VehicleTypeGroups)
    var vehicleTypeGroup by VehicleTypeGroups.name
}

object MfrBodyCodes : IntIdTable("mfrbodycode","MfrBodyCodeID"){
    val name = varchar ("MfrBodyCodeName",10)
}

class MfrBodyCode(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MfrBodyCode>(MfrBodyCodes)
    var mfrBodyCode by MfrBodyCodes.name
}

object BodyNumDoors : IntIdTable("bodynumdoors","BodyNumDoorsID"){
    val numDoor = varchar ("BodyNumDoors",3)
}

class BodyNumDoor(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BodyNumDoor>(BodyNumDoors)
    var bodyNumDoor by BodyNumDoors.numDoor
}

object BodyTypes : IntIdTable("bodytype","BodyTypeID"){
    val name = varchar ("BodyTypeName",50)
}
class BodyType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BodyType>(BodyTypes)
    var bodyType by BodyTypes.name
}

object DriveTypes : IntIdTable("drivetype","DriveTypeID"){
    val name = varchar ("DriveTypeName",30)
}

class DriveType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DriveType>(DriveTypes)
    var driveType by DriveTypes.name
}

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

object EngineDesignations : IntIdTable("enginedesignation","EngineDesignationID"){
    val name = varchar ("EngineDesignationName",30)
}

class EngineDesignation(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineDesignation>(EngineDesignations)
    var engineDesignation by EngineDesignations.name
}

object EngineMfrs : IntIdTable("enginemfr","EngineMfrID"){
    val name = varchar ("EngineMfrName",50)
}

class EngineMfr(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineMfr>(EngineMfrs)
    var engineMfr by EngineMfrs.name
}

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

object FuelSystemDesigns : IntIdTable("fuelsystemdesign","FuelSystemDesignID"){
    val name = varchar ("FuelSystemDesignName",50)
}

class FuelSystemDesign(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelSystemDesign>(FuelSystemDesigns)
    var fuelSystemDesign by FuelSystemDesigns.name
}

object FuelSystemControlTypes : IntIdTable("fuelsystemcontroltype","FuelSystemControlTypeID"){
    val name = varchar ("FuelSystemControlTypeName",50)
}

class FuelSystemControlType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelSystemControlType>(FuelSystemControlTypes)
    var fuelSystemControlType by FuelSystemControlTypes.name
}

object FuelDeliverySubTypes : IntIdTable("fueldeliverysubtype","FuelDeliverySubTypeID"){
    val name = varchar ("FuelDeliverySubTypeName",50)
}

class FuelDeliverySubType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelDeliverySubType>(FuelDeliverySubTypes)
    var fuelDeliverySubType by FuelDeliverySubTypes.name
}

object FuelDeliveryTypes : IntIdTable("fueldeliverytype","FuelDeliveryTypeID"){
    val name = varchar ("FuelDeliveryTypeName",50)
}

class FuelDeliveryType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelDeliveryType>(FuelDeliveryTypes)
    var fuelDeliveryType by FuelDeliveryTypes.name
}

object Aspirations : IntIdTable("aspiration","AspirationID"){
    val name = varchar ("AspirationName",30)
}
class Aspiration(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Aspiration>(Aspirations)
    var aspiration by Aspirations.name
}

object CylinderHeadTypes : IntIdTable("cylinderheadtype","CylinderHeadTypeID"){
    val name = varchar ("CylinderHeadTypeName",30)
}

class CylinderHeadType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CylinderHeadType>(CylinderHeadTypes)
    var cylinderHeadType by CylinderHeadTypes.name
}

object FuelTypes : IntIdTable("fueltype","FuelTypeID"){
    val name = varchar ("FuelTypeName",30)
}
class FuelType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuelType>(FuelTypes)
    var fuelType by FuelTypes.name
}

object IgnitionSystemTypes : IntIdTable("IgnitionSystemType","IgnitionSystemTypeID"){
    val name = varchar ("IgnitionSystemTypeName",30)
}
class IgnitionSystemType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IgnitionSystemType>(IgnitionSystemTypes)
    var ignitionSystemType by IgnitionSystemTypes.name
}

object TransmissionTypes : IntIdTable("transmissiontype","TransmissionTypeID"){
    val name = varchar ("TransmissionTypeName",30)
}

class TransmissionType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionType>(TransmissionTypes)
    var transmissionType by TransmissionTypes.name
}

object TransmissionControlTypes : IntIdTable("transmissioncontroltype","TransmissionControlTypeID"){
    val name = varchar ("TransmissionControlTypeName",30)
}

class TransmissionControlType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionControlType>(TransmissionControlTypes)
    var transmissionControlType by TransmissionControlTypes.name
}

object TransmissionMfrCodes : IntIdTable("transmissionmfrcode","TransmissionMfrCodeID"){
    val name = varchar ("TransmissionMfrCode",30)
}

class TransmissionMfrCode(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionMfrCode>(TransmissionMfrCodes)
    var transmissionMfrCode by TransmissionMfrCodes.name
}

object TransmissionNumSpeeds : IntIdTable("transmissionnumspeeds"){
    val numbSpeed = varchar("TransmissionNumSpeeds",3)
}

class TransmissionNumSpeed(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TransmissionNumSpeed>(TransmissionNumSpeeds)
    var transmissionNumSpeed by TransmissionNumSpeeds.numbSpeed
}

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

object BedLengths : IntIdTable("bedlength","BedLengthID"){
    val name = varchar ("BedLength",10)
    val metric = varchar("BedLengthMetric",10)
}

class BedLength(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BedLength>(BedLengths)
    var bedLengthName by BedLengths.name
    var bedMetric by BedLengths.metric
}

object BedTypes : IntIdTable("bedtype","BedTypeID"){
    val name = varchar ("BedTypeName",50)
}

class BedType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BedType>(BedTypes)
    var bedType by BedLengths.name
}

object BedConfigs : IntIdTable("bedconfig","BedConfigID"){
    val bedLength_id = reference("BedLengthID",BedLengths)
    val bedType_id = reference("BedTypeID",BedTypes)
}

class BedConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BedConfig>(BedConfigs)

    var bedLength by BedLength referencedOn BedConfigs.bedLength_id
    var bedType by BedType referencedOn BedConfigs.bedType_id
}

object WheelBases : IntIdTable("wheelbase","WheelBaseID"){
    val name = varchar ("WheelBase",10)
    val metric = varchar("WheelBaseMetric",10)
}

class WheelBase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WheelBase>(WheelBases)
    var wheelBaseName by WheelBases.name
    var wheelBaseMetric by WheelBases.metric
}

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

object BrakeSystems : IntIdTable("brakesystem","BrakeSystemID" ){
    val name = varchar("BrakeSystemName",30)
}

class BrakeSystem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrakeSystem>(BrakeSystems)
    var brakeSystem by BrakeSystems.name
}

object BrakeTypes : IntIdTable("braketype","BrakeTypeID"){
    val name = varchar("BrakeTypeName",30)
}
class BrakeType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrakeType>(BrakeTypes)
    var brakeType by BrakeTypes.name
}

object BrakeAbs : IntIdTable("brakeabs", "BrakeABSID"){
    val name = varchar("BrakeABSName",30)
}

class BrakeABS(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrakeABS>(BrakeAbs)
    var brakeAbs by BrakeAbs.name
}

object SpringTypes : IntIdTable("springtype","SpringTypeID"){
    val name = varchar("SpringTypeName",50)
}

class SpringType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SpringType>(SpringTypes)
    var springType by SpringTypes.name
}

object SpringTypeConfigs : IntIdTable("springtypeconfig","SpringTypeConfigID"){
    val frontSpringType_id = reference("FrontSpringTypeID",SpringTypes)
    val rearSpringType_id = reference("RearSpringTypeID",SpringTypes)
}

class SpringTypeConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SpringTypeConfig>(SpringTypeConfigs)
    var frontSpringType by SpringType referencedOn SpringTypeConfigs.frontSpringType_id
    var rearSpringType by SpringType referencedOn SpringTypeConfigs.rearSpringType_id
}

object SteeringTypes : IntIdTable("steeringtype","SteeringTypeID"){
    val name = varchar("SteeringTypeName",30)
}
class SteeringType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SteeringType>(SteeringTypes)
    var steeringType by SteeringTypes.name
}

object SteeringSystems : IntIdTable("steeringsystem","SteeringSystemID"){
    val name = varchar("SteeringSystemName",30)
}

class SteeringSystem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SteeringSystem>(SteeringSystems)
    var steeringSystem by SteeringSystems.name
}

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

object EngineVersions: IntIdTable("engineversion","EngineVersionID" ){
    val version = varchar("EngineVersion",20)
}

class EngineVersion(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineVersion>(EngineVersions)
    var engineVersion by EngineVersions.version

}

object PowerOutputs: IntIdTable("poweroutput","PowerOutputID"){
    val horsePower = varchar("HorsePower",10)
    val kilowattPower = varchar("KilowattPower",10)
}

class PowerOutput(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PowerOutput>(PowerOutputs)
    var horsePower by PowerOutputs.horsePower
    var kilowattPower by PowerOutputs.kilowattPower

}

object PublicationStages: IntIdTable("publicationstage","PublicationStageID"){
    val name = varchar("PublicationStageName",100)
}

class PublicationStage(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PublicationStage>(PublicationStages)
    var publicationStageName by PublicationStages.name
}

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

object BodyStyleConfigs: IntIdTable("bodystyleconfig","BodyStyleConfigID"){
    val bodyNumDoors_id = reference("BodyNumDoorsID",BodyNumDoors)
    val bodyType_id = reference("BodyTypeID",BodyTypes)
}

class BodyStyleConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BodyStyleConfig>(BodyStyleConfigs)
    var bodyNumDoors by BodyNumDoor referencedOn BodyStyleConfigs.bodyNumDoors_id
    var bodyType by BodyType referencedOn BodyStyleConfigs.bodyType_id

}

object SteeringConfigs: IntIdTable("steeringconfig","SteeringConfigID"){
    val steeringType_id = reference("SteeringTypeID",SteeringTypes)
    val steeringSystem_id = reference("SteeringSystemID",SteeringSystems)
}
class SteeringConfig(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SteeringConfig>(SteeringConfigs)
    var steeringType by SteeringType referencedOn SteeringConfigs.steeringType_id
    var steeringSystem by SteeringSystem referencedOn SteeringConfigs.steeringSystem_id

}

object Valves: IntIdTable("valves","ValvesID"){
    val valvesPerEngine = varchar("ValvesPerEngine",3)
}

class Valve(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Valve>(Valves)
    var valvesPerEngine by Valves.valvesPerEngine
}

object Mfrs: IntIdTable("mfr","MfrID"){
    val name = varchar("MfrName",30)
}

class Mfr(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Mfr>(Mfrs)
    var name by Mfrs.name
}

object EngineVINs: IntIdTable("enginevin","EngineVINID"){
    val name = varchar("EngineVINName",5)
}

class EngineVIN(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EngineVIN>(EngineVINs)
    var name by EngineVINs.name
}

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

object ElecControlleds: IntIdTable("eleccontrolled","ElecControlledID"){
    val name = varchar("ElecControlled",3)
}

class ElecControlled(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ElecControlled>(ElecControlleds)
    var name by ElecControlleds.name
}

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

object Versions: IntIdTable("version","id"){
    val versionDate = date("VersionDate")
}
class Version(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Version>(Versions)
    var versionDate by Versions.versionDate
}

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

object ChangeReasons: IntIdTable("changereasons","ChangeReasonID"){
    val name = varchar("ChangeReason",255)
}

class ChangeReason(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChangeReason>(ChangeReasons)
    var name by ChangeReasons.name
}

object ChangeAttributeStates: IntIdTable("changeattributestates","ChangeAttributeStateID"){
    val name = varchar("ChangeAttributeState",255)
}

class ChangeAttributeState(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChangeAttributeState>(ChangeAttributeStates)
    var name by ChangeAttributeStates.name
}

object ChangeTableNames: IntIdTable("changetablenames","TableNameID"){
    val name = varchar("TableName",255)
    val name_Description = varchar("TableDescription",255)
}

class ChangeTableName(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChangeTableName>(ChangeTableNames)
    var name by ChangeTableNames.name
    var name_Desc by ChangeTableNames.name_Description
}

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

object Abbreviations: IntIdTable("abbreviation","Abbreviation"){
    val name_Description = varchar("Description",20)
    val name_LongDescription = varchar("LongDescription",200)
}

class Abbreviation(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Abbreviation>(Abbreviations)
    var name_Desc by Abbreviations.name_Description
    var name_LongDesc by Abbreviations.name_LongDescription
}

object EnglishPhrases: IntIdTable("englishphrase","EnglishPhraseID"){
    val englishPhrase = varchar("EnglishPhrase",100)
}

class EnglishPhrase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EnglishPhrase>(EnglishPhrases)
    var englishPhrase by EnglishPhrases.englishPhrase
}

object Languages: IntIdTable("language","LanguageID" ){
    val language_name = varchar("LanguageName",20)
    val dialectName = varchar("DialectName",20)
}

class Language(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Language>(Languages)
    var language_name by Languages.language_name
    var dialectName by Languages.dialectName
}

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

object LanguageTranslationAttachments: IntIdTable("languagetranslationattachment","LanguageTranslationAttachmentID"){
    val languageTranslation_id = reference("LanguageTranslationID",LanguageTranslations)
    val attachment_id = reference("AttachmentID",Attachments)
}

class LanguageTranslationAttachment(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LanguageTranslationAttachment>(LanguageTranslationAttachments)
    var languageTranslation by LanguageTranslationAttachments.languageTranslation_id
    var attachment by LanguageTranslationAttachments.attachment_id
}

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
        println("BaseVehicle: ${BaseVehicle.findById(1)?.year?.id}")
        println("Year: ${Year.findById(2009)?.id}")
        println("DriveType: ${DriveType.findById(1)?.id}")
    }
}