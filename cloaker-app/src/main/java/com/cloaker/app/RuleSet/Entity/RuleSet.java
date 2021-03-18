package com.cloaker.app.RuleSet.Entity;

import com.cloaker.app.POJO.FilterData.RuleSetData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity(name = "RULESET_TABLE")
public class RuleSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rulesetId;

    @NonNull
    private String rulesetName;

    @Lob
    @NonNull
    private RuleSetData ruleset;

    public int getRulesetId() {
        return rulesetId;
    }

    public String getRulesetName() {
        return rulesetName;
    }

    public void setRulesetName(String rulesetName) {
        this.rulesetName = rulesetName;
    }

    public RuleSetData getRuleset() {
        return ruleset;
    }

    public void setRuleset(RuleSetData ruleset) {
        this.ruleset = ruleset;
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
