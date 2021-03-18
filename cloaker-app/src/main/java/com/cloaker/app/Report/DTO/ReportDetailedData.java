package com.cloaker.app.Report.DTO;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public interface ReportDetailedData {

    Integer getCampaignId();
    String getCampaignName();
    Boolean getPassFail();
    String getFailedAt();
    Timestamp getCheckedOn();
}
