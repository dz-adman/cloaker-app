package com.cloaker.app.Report.DTO;

import org.springframework.stereotype.Component;

@Component
public interface ReportData {

    Integer getCampaignId();
    String getCampaignName();
    Boolean getPassFail();
    Integer getPassFailCount();
}
