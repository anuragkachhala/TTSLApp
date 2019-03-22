package com.software.ttsl.model;

public class GetQuotationFormData {

    private int weight;
    private int volume;
    private int mUN_Numuber;
    private int no_of_container;
    private String shippingMode;
    private String weightUnit;
    private String volumeUnit;
    private String m_IMO_Class;
    private String shippingDetails;
    private String containerType;
    private String cargoType;
    private String wagonType;
    private String commodityType;
    private String packingType;
    private String sourcePort;
    private String destinationPort;
    private boolean transportType;
    private boolean shipment;

    public String getShippingMode() {
        return shippingMode;
    }

    public void setShippingMode(String shippingMode) {
        this.shippingMode = shippingMode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getUN_Numuber() {
        return mUN_Numuber;
    }

    public void setUN_Numuber(int UN_Numuber) {
        this.mUN_Numuber = UN_Numuber;
    }

    public int getNo_of_container() {
        return no_of_container;
    }

    public void setNo_of_container(int no_of_container) {
        this.no_of_container = no_of_container;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    public String getIMO_Class() {
        return m_IMO_Class;
    }

    public void setM_IMO_Class(String IMO_Class) {
        this.m_IMO_Class = IMO_Class;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public String getWagonType() {
        return wagonType;
    }

    public void setWagonType(String wagonType) {
        this.wagonType = wagonType;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public boolean isTransportType() {
        return transportType;
    }

    public void setTransportType(boolean transportType) {
        this.transportType = transportType;
    }

    public boolean isShipment() {
        return shipment;
    }

    public void setShipment(boolean shipment) {
        this.shipment = shipment;
    }
}
