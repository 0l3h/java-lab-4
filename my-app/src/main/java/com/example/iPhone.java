package com.example;

public class IPhone extends SmartPhone {
    private String faceIdVersion;

    public IPhone(String model, String faceIdVersion) {
        super("Apple", model, "iOS");
        this.faceIdVersion = faceIdVersion;
    }

    @Override
    public String toString() {
        return super.toString() + ", FaceID: " + faceIdVersion;
    }
}