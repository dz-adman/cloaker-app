package com.cloaker.app.POJO.FilterData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ReferrerFilterData implements Serializable {

    private static final long serialVersionUID = -5116769950688098850L;
    private boolean allowBlockReferrer;
    private List<String> referrers;
    private boolean spoofIt;

    public boolean isAllowBlockReferrer() {
        return allowBlockReferrer;
    }

    public void setAllowBlockReferrer(boolean allowBlockReferrer) {
        this.allowBlockReferrer = allowBlockReferrer;
    }

    public List<String> getReferrers() {
        return referrers;
    }

    public void setReferrers(List<String> referrers) {
        this.referrers = referrers;
    }

    public boolean isSpoofIt() {
        return spoofIt;
    }

    public void setSpoofIt(boolean spoofIt) {
        this.spoofIt = spoofIt;
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
