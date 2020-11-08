package com.casterlabs.greenmart.model.endusers;

public class CustomerLocation {

    private String customerLocationAddress;
    private String customerLocationLat;
    private String customerLocationLng;

    public String getCustomerLocationAddress() {
        return customerLocationAddress;
    }

    public void setCustomerLocationAddress(String customerLocationAddress) {
        this.customerLocationAddress = customerLocationAddress;
    }

    public String getCustomerLocationLat() {
        return customerLocationLat;
    }

    public void setCustomerLocationLat(String customerLocationLat) {
        this.customerLocationLat = customerLocationLat;
    }

    public String getCustomerLocationLng() {
        return customerLocationLng;
    }

    public void setCustomerLocationLng(String customerLocationLng) {
        this.customerLocationLng = customerLocationLng;
    }
}
