package com.cloaker.app.POJO.FilterData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Checks implements Serializable {

    private static final long serialVersionUID = -3650646703834512359L;
    private boolean deviceCheck;
    private boolean ipRepCheck;
    private boolean ispCheck;
    private boolean locationCheck;
    private boolean advancedLocationCheck;
    private boolean proxyVpnCheck;
    private boolean referrerCheck;
    private boolean referrerSpoof;
    private boolean tagUser;

    public boolean isDeviceCheck() {
        return deviceCheck;
    }

    public void setDeviceCheck(boolean deviceCheck) {
        this.deviceCheck = deviceCheck;
    }

    public boolean isIpRepCheck() {
        return ipRepCheck;
    }

    public void setIpRepCheck(boolean ipRepCheck) {
        this.ipRepCheck = ipRepCheck;
    }

    public boolean isIspCheck() {
        return ispCheck;
    }

    public void setIspCheck(boolean ispCheck) {
        this.ispCheck = ispCheck;
    }

    public boolean isLocationCheck() {
        return locationCheck;
    }

    public void setLocationCheck(boolean locationCheck) {
        this.locationCheck = locationCheck;
    }

    public boolean isAdvancedLocationCheck() {
        return advancedLocationCheck;
    }

    public void setAdvancedLocationCheck(boolean advancedLocationCheck) {
        this.advancedLocationCheck = advancedLocationCheck;
    }

    public boolean isProxyVpnCheck() {
        return proxyVpnCheck;
    }

    public void setProxyVpnCheck(boolean proxyVpnCheck) {
        this.proxyVpnCheck = proxyVpnCheck;
    }

    public boolean isReferrerCheck() {
        return referrerCheck;
    }

    public void setReferrerCheck(boolean referrerCheck) {
        this.referrerCheck = referrerCheck;
    }

    public boolean isReferrerSpoof() {
        return referrerSpoof;
    }

    public void setReferrerSpoof(boolean referrerSpoof) {
        this.referrerSpoof = referrerSpoof;
    }

    public boolean isTagUser() {
        return tagUser;
    }

    public void setTagUser(boolean tagUser) {
        this.tagUser = tagUser;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
