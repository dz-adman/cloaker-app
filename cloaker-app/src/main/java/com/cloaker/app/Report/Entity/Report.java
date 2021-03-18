package com.cloaker.app.Report.Entity;

import com.cloaker.app.Campaign.Entity.Campaign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "REPORT_TABLE")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "campaign", referencedColumnName = "campaignId")
    private Campaign campaign;

    @NotNull
    private boolean passFail;

    @Column(columnDefinition = "LONGTEXT")
    private String failedAt;

    @NotNull
    private Timestamp checkedOn;

    public int getReportId() {
        return reportId;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public boolean isPassFail() {
        return passFail;
    }

    public void setPassFail(boolean passFail) {
        this.passFail = passFail;
    }

    public String getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(String failedAt) {
        this.failedAt = failedAt;
    }

    public Timestamp getCheckedOn() {
        return checkedOn;
    }

    public void setCheckedOn(Timestamp checkedOn) {
        this.checkedOn = checkedOn;
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
