package com.cloaker.app.POJO.FilterData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class DeviceFilterData implements Serializable {

    private static final long serialVersionUID = -3184342221589024886L;
    private boolean isAllowedMobile;
    private boolean isAllowedDesktop;
    private boolean isAllowedTablet;
    private boolean isAllowedOther;
    private boolean allowBlockBrowsers;
    private List<String> browsers;
    private boolean allowBlockOs;
    private List<String> os;

    public boolean isAllowedMobile() {
        return isAllowedMobile;
    }

    public void setAllowedMobile(boolean allowedMobile) {
        isAllowedMobile = allowedMobile;
    }

    public boolean isAllowedDesktop() {
        return isAllowedDesktop;
    }

    public void setAllowedDesktop(boolean allowedDesktop) {
        isAllowedDesktop = allowedDesktop;
    }

    public boolean isAllowedTablet() {
        return isAllowedTablet;
    }

    public void setAllowedTablet(boolean allowedTablet) {
        isAllowedTablet = allowedTablet;
    }

    public boolean isAllowedOther() {
        return isAllowedOther;
    }

    public void setAllowedOther(boolean allowedOther) {
        isAllowedOther = allowedOther;
    }

    public boolean isAllowBlockBrowsers() {
        return allowBlockBrowsers;
    }

    public void setAllowBlockBrowsers(boolean allowBlockBrowsers) {
        this.allowBlockBrowsers = allowBlockBrowsers;
    }

    public List<String> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(List<String> browsers) {
        this.browsers = browsers;
    }

    public boolean isAllowBlockOs() {
        return allowBlockOs;
    }

    public void setAllowBlockOs(boolean allowBlockOs) {
        this.allowBlockOs = allowBlockOs;
    }

    public List<String> getOs() {
        return os;
    }

    public void setOs(List<String> os) {
        this.os = os;
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
