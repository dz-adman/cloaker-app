package com.cloaker.app.POJO.ThirdParty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class Connection {

    private String connection_type;
    private String autonomous_system_number;
    private String autonomous_system_organization;
    private String isp_name;
    private String organization_name;

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public String getAutonomous_system_number() {
        return autonomous_system_number;
    }

    public void setAutonomous_system_number(String autonomous_system_number) {
        this.autonomous_system_number = autonomous_system_number;
    }

    public String getAutonomous_system_organization() {
        return autonomous_system_organization;
    }

    public void setAutonomous_system_organization(String autonomous_system_organization) {
        this.autonomous_system_organization = autonomous_system_organization;
    }

    public String getIsp_name() {
        return isp_name;
    }

    public void setIsp_name(String isp_name) {
        this.isp_name = isp_name;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
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
