package com.example;

public class SatPhone extends KeypadPhone {
    private String satelliteNetwork;

    public SatPhone(String brand, String model, String satelliteNetwork) {
        super(brand, model, true);
        this.satelliteNetwork = satelliteNetwork;
    }

    public String getSatelliteNetwork() { return satelliteNetwork; }

    @Override
    public String toString() {
        return super.toString() + ", Супутникова мережа: " + satelliteNetwork;
    }
}