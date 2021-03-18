package com.cloaker.app.Service;

import com.cloaker.app.Campaign.DAO.CampaignDAO;
import com.cloaker.app.Campaign.Entity.Campaign;
import com.cloaker.app.POJO.FilterData.Checks;
import com.cloaker.app.POJO.FilterData.RuleSetData;
import com.cloaker.app.POJO.ThirdParty.GeoLocation;
import com.cloaker.app.Report.Service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class MainService {

    @Autowired
    InitRuleSetPOJOs initRuleSetPOJOs;

    @Autowired
    InitPOJO initPOJO;

    @Autowired
    LocationService locationService;

    @Autowired
    IspService ispService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    ProxyVpnService proxyVpnService;

    @Autowired
    ReferrerService referrerService;

    @Autowired
    TagUserService tagUserService;

    @Autowired
    BlacklistService blacklistService;

    @Autowired
    IpReputationService ipReputationService;

    @Autowired
    ReportService reportService;

    @Autowired
    CampaignDAO campaignDAO;

    final OkHttpClient client = new OkHttpClient();

    Request req = new Request.Builder()
            .url("https://www.some-user-affiliate-url-from-campaign.com")
            .get().build();

    public String start(HttpServletRequest request, HttpServletResponse response, String url) {
        final Predicate<String> predicateCookieName = x -> x.equals("cloakerAppCookie");
        final Predicate<String> predicateCookieValueGood = x -> x.equals("Good");
        final Predicate<String> predicateCookieValueBad = x -> x.equals("Bad");

        Optional<Campaign> campaign = campaignDAO.findByUrl(url);
        if(campaign.isPresent()) {
            try {
                for (Cookie cookie : request.getCookies()) {
                    if (predicateCookieName.test(cookie.getName())) {
                        if (predicateCookieValueGood.test(cookie.getValue())) {
                            saveResultData(url, "{\"RECURRINGUSER\":\"true\"");
                            return campaign.get().getOriginalUrl();
                        }
                        if (predicateCookieValueBad.test(cookie.getValue())) {
                            saveResultData(url, "{\"RECURRINGUSER\":\"false\"");
                            return campaign.get().getFallbackUrl();
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("\nEXCEPTION::[Cookies' Invocation Failed!]");
            }

            RuleSetData ruleSetData = initRuleSetPOJOs.loadRuleSetToPOJOs(url);
            GeoLocation geoLocation = initPOJO.initIpLocationPOJOs(request);

            Map<String, Map<String, Boolean>> filterStatus = applyFilters(ruleSetData, geoLocation, request, response);

            String reportJsonString = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                reportJsonString = objectMapper.writeValueAsString(filterStatus);
                System.out.println(reportJsonString);
            } catch (Exception ex) {
                System.out.println("\nEXCEPTION: [Unable to parse Report-Map to Json String!]");
            }
            saveResultData(url, reportJsonString);

            // use req object (with conditionally spoofed referer) to make OkHttpClient request at original/fallback url
            if (!filterStatus.containsValue(false))
                return campaign.get().getOriginalUrl();
            return campaign.get().getFallbackUrl();
        }
        return url;
    }

    @NotNull
    private Map<String, Map<String, Boolean>> applyFilters(@NotNull RuleSetData ruleSetData, GeoLocation geoLocation, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Map<String, Boolean>> filterStatus = new HashMap<>();

        Checks checks = ruleSetData.getChecks();

        if(checks.isDeviceCheck()) {
            Map<String, Boolean> deviceReport = deviceService.deviceFilter(ruleSetData.getDeviceFilterData(), request.getHeader("User-Agent"));
            filterStatus.put("DEVICE", deviceReport);
        }
        if(checks.isLocationCheck()) {
            Map<String, Boolean> locationReport = locationService.locationFilter(ruleSetData.getLocationFilterData(), geoLocation);
            filterStatus.put("LOCATION", locationReport);
        }
        if(checks.isAdvancedLocationCheck()) {
            Map<String, Boolean> advLocReport = locationService.advancedLocationFilter(ruleSetData.getLocationFilterData(), geoLocation);
            filterStatus.put("ADVANCEDLOCATION", advLocReport);
        }
        if(checks.isIspCheck()) {
            Map<String, Boolean> ispReport = ispService.ispFilter(ruleSetData.getIspFilterData(), geoLocation.getConnection());
            filterStatus.put("ISP", ispReport);
        }
        if(checks.isProxyVpnCheck()) {
            Map<String, Boolean> proxyVpnReport = proxyVpnService.proxyVpnFilter(ruleSetData.getProxyVpnFilterData(), geoLocation.getSecurity());
            filterStatus.put("PROXYVPN", proxyVpnReport);
        }
        if(checks.isReferrerCheck()) {
            Map<String, Boolean> refererReport = referrerService.referrerFilter(ruleSetData.getReferrerFilterData(), request.getHeader("referer"));
            filterStatus.put("REFERER", refererReport);
        }
        if(checks.isReferrerSpoof())
            req = referrerService.spoofReferrer(req);
        if(checks.isIpRepCheck()) {
            Map<String, Boolean> ipRepReport = ipReputationService.checkIpRep();
            filterStatus.put("IPREPUTATION", ipRepReport);
        }

        Map<String, Boolean> ipBlacklistReport = blacklistService.ipBlacklistFilter(geoLocation.getIp_address());
        filterStatus.put("IPBLACKLIST", ipBlacklistReport);

        Map<String, Boolean> ispBlacklistReport = blacklistService.ispBlacklistFilter(geoLocation.getConnection().getIsp_name(), geoLocation.getConnection().getOrganization_name());
        filterStatus.put("ISPBLACKLIST", ispBlacklistReport);

        if(checks.isTagUser())
            response.addCookie(tagUserService.setUserCookies(req, !filterStatus.containsValue(false)));

        return filterStatus;
    }

    private void saveResultData(String url, String reportJsonString) {
        reportService.saveReportData(url, reportJsonString);
    }
}
