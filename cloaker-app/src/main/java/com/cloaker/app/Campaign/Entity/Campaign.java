package com.cloaker.app.Campaign.Entity;

import com.cloaker.app.RuleSet.Entity.RuleSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "CAMPAIGN_TABLE")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignId;

    @NonNull
    private String campaignName;

    @ManyToOne
    @JoinColumn(name = "rulesetId", referencedColumnName = "rulesetId")
    private RuleSet ruleset;

    @NonNull
    private String url;

    @NonNull
    private String originalUrl;

    @NonNull
    private String fallbackUrl;

    @NonNull
    private Date createdOn;

    private Date lastUpdated;

    public int getCampaignId() {
        return campaignId;
    }

    @NonNull
    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(@NonNull String campaignName) {
        this.campaignName = campaignName;
    }

    public RuleSet getRuleset() {
        return ruleset;
    }

    public void setRuleset(RuleSet ruleset) {
        this.ruleset = ruleset;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(@NonNull String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @NonNull
    public String getFallbackUrl() {
        return fallbackUrl;
    }

    public void setFallbackUrl(@NonNull String fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
    }

    @NonNull
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(@NonNull Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
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
