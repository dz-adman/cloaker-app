package com.cloaker.app.Controller;

import com.cloaker.app.Report.Service.ReportService;
import com.cloaker.app.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @Autowired
    ReportService reportService;

    @GetMapping(value = "/home")
    public String home(HttpServletRequest request, HttpServletResponse response, @RequestParam String url) {
        String responsePageUrl = mainService.start(request, response, url);
            return responsePageUrl;
    }

    @GetMapping(value = "/fetchReportData", produces = "application/json")
    public List<Object> fetchReportData() {
        return reportService.fetchReportData();
    }

    @GetMapping(value = "/fetchCampaignReportData/{campaignId}", produces = "application/json")
    public List<Object> fetchReportData(@PathVariable int campaignId) {
        return reportService.fetchReportData(campaignId);
    }

    @GetMapping(value = "/fetchReportData/{reportDate}", produces = "application/json")
    public List<Object> fetchReportData(@PathVariable String reportDate) {
        return reportService.fetchReportData(reportDate);
    }

    @GetMapping(value = "/fetchReportForDuration", produces = "application/json")
    public List<Object> fetchReportForDurations(@RequestParam String startDate, @RequestParam String endDate) {
        return reportService.fetchReportData(startDate, endDate);
    }
}
