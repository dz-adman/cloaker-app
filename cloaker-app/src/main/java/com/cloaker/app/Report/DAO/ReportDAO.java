package com.cloaker.app.Report.DAO;


import com.cloaker.app.Report.DTO.ReportData;
import com.cloaker.app.Report.DTO.ReportDetailedData;
import com.cloaker.app.Report.Entity.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ReportDAO extends CrudRepository<Report, Integer> {

    @Query(value = "SELECT r.campaign.campaignId AS campaignId, r.campaign.campaignName AS campaignName, r.passFail AS passFail, COUNT(r.passFail) AS passFailCount FROM Report r GROUP BY r.campaign.campaignId, r.passFail")
    public Collection<ReportData> fetchCampaignWiseReport();

    @Query(value = "SELECT r.campaign.campaignId AS campaignId, r.campaign.campaignName AS campaignName, r.passFail AS passFail, r.failedAt AS failedAt, r.checkedOn AS checkedOn FROM Report r WHERE r.campaign.campaignId = ?1")
    public Collection<ReportDetailedData> fetchCampaignReport(int campaignId);

    @Query(value = "SELECT r.campaign.campaignId AS campaignId, r.campaign.campaignName AS campaignName, r.passFail AS passFail, COUNT(r.passFail) AS passFailCount FROM Report r WHERE substr(r.checkedOn, 1, 10) = ?1 GROUP BY r.campaign.campaignId, r.passFail")
    public Collection<ReportData> fetchReportForDate(String reportDate);

    @Query(value = "SELECT r.campaign.campaignId AS campaignId, r.campaign.campaignName AS campaignName, r.passFail AS passFail, COUNT(r.passFail) AS passFailCount FROM Report r WHERE substr(r.checkedOn, 1, 10) >= ?1 AND substr(r.checkedOn, 1, 10) <= ?2 GROUP BY r.campaign.campaignId, r.passFail")
    public Collection<ReportData> fetchReportForDuration(String startDate, String endDate);
}
