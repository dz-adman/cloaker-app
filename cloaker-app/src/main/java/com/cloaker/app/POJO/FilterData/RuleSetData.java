package com.cloaker.app.POJO.FilterData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RuleSetData implements Serializable {

    private static final long serialVersionUID = -5226781975923534597L;
    private Checks checks;
    private DeviceFilterData deviceFilterData;
    private IspFilterData ispFilterData;
    private LocationFilterData locationFilterData;
    private ProxyVpnFilterData proxyVpnFilterData;
    private ReferrerFilterData referrerFilterData;

    public Checks getChecks() {
        return checks;
    }

    public void setChecks(Checks checks) {
        this.checks = checks;
    }

    public DeviceFilterData getDeviceFilterData() {
        return deviceFilterData;
    }

    public void setDeviceFilterData(DeviceFilterData deviceFilterData) {
        this.deviceFilterData = deviceFilterData;
    }

    public IspFilterData getIspFilterData() {
        return ispFilterData;
    }

    public void setIspFilterData(IspFilterData ispFilterData) {
        this.ispFilterData = ispFilterData;
    }

    public LocationFilterData getLocationFilterData() {
        return locationFilterData;
    }

    public void setLocationFilterData(LocationFilterData locationFilterData) {
        this.locationFilterData = locationFilterData;
    }

    public ProxyVpnFilterData getProxyVpnFilterData() {
        return proxyVpnFilterData;
    }

    public void setProxyVpnFilterData(ProxyVpnFilterData proxyVpnFilterData) {
        this.proxyVpnFilterData = proxyVpnFilterData;
    }

    public ReferrerFilterData getReferrerFilterData() {
        return referrerFilterData;
    }

    public void setReferrerFilterData(ReferrerFilterData referrerFilterData) {
        this.referrerFilterData = referrerFilterData;
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
