package com.cloaker.app.POJO.FilterData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class IspFilterData implements Serializable {

    private static final long serialVersionUID = -342365527516766370L;
    private boolean allowBlockIsp;
    private List<String> blockedIspList;

    public boolean isAllowBlockIsp() {
        return allowBlockIsp;
    }

    public void setAllowBlockIsp(boolean allowBlockIsp) {
        this.allowBlockIsp = allowBlockIsp;
    }

    public List<String> getBlockedIspList() {
        return blockedIspList;
    }

    public void setBlockedIspList(List<String> blockedIspList) {
        this.blockedIspList = blockedIspList;
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
