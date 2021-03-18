package com.cloaker.app.Report.Service;

import com.cloaker.app.Campaign.DAO.CampaignDAO;
import com.cloaker.app.Campaign.Entity.Campaign;
import com.cloaker.app.Report.DAO.ReportDAO;
import com.cloaker.app.Report.Entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    ReportDAO reportDAO;

    @Autowired
    CampaignDAO campaignDAO;

    public void saveReportData(String url, String reportJsonString) {
        Optional<Campaign> campaign = campaignDAO.findByUrl(url);
        Report report = setReportRowData(campaign.get(), reportJsonString);
        reportDAO.save(report);
    }
    public List<Object> fetchReportData() {
        return reportDAO.fetchCampaignWiseReport().stream().collect(Collectors.toList());
    }
    public List<Object> fetchReportData(String reportDate) {
        return reportDAO.fetchReportForDate(reportDate).stream().collect(Collectors.toList());
    }
    public List<Object> fetchReportData(int campaignId) {
        return reportDAO.fetchCampaignReport(campaignId).stream().collect(Collectors.toList());
    }
    public List<Object> fetchReportData(String startDate, String endDate) {
        return reportDAO.fetchReportForDuration(startDate, endDate).stream().collect(Collectors.toList());
    }

    private Report setReportRowData(Campaign campaign, String reportJsonString) {
        Report report = new Report();
        boolean passFail = true;
        if(reportJsonString.isEmpty() || reportJsonString == null)  return null;
        report.setFailedAt(reportJsonString);
        report.setCampaign(campaign);
        report.setPassFail(!reportJsonString.toLowerCase().contains("false"));
        report.setCheckedOn(new Timestamp(System.currentTimeMillis()));
        return report;
    }
}
