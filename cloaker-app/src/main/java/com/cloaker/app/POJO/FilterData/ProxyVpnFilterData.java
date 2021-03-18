package com.cloaker.app.POJO.FilterData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ProxyVpnFilterData implements Serializable {

    private static final long serialVersionUID = 7267492164831051831L;
    private boolean isProxyVpnAllowed;

    public boolean isProxyVpnAllowed() {
        return isProxyVpnAllowed;
    }

    public void setProxyVpnAllowed(boolean proxyVpnAllowed) {
        isProxyVpnAllowed = proxyVpnAllowed;
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
