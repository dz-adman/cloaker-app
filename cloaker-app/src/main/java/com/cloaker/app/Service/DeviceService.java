package com.cloaker.app.Service;

import com.cloaker.app.POJO.FilterData.DeviceFilterData;
import eu.bitwalker.useragentutils.UserAgent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class DeviceService {

    public Map<String, Boolean> deviceFilter(@NotNull DeviceFilterData deviceFilterData, @NotNull String userAgentVal) {

        final UserAgent userAgent = UserAgent.parseUserAgentString(userAgentVal);
        final String deviceType = userAgent.getOperatingSystem().getDeviceType().getName();

        final Predicate<String> predicateMobile = x -> x.equalsIgnoreCase("Mobile");
        final Predicate<String> predicateTablet = x -> x.equalsIgnoreCase("Tablet");
        final Predicate<String> predicateComputer = x -> x.equalsIgnoreCase("Computer");
        final Predicate<String> predicateBrowser = x -> userAgent.getBrowser().getName().toLowerCase().contains(x.toLowerCase()) || x.toLowerCase().contains(userAgent.getBrowser().getName().toLowerCase());
        final Predicate<String> predicateOs = x -> userAgent.getOperatingSystem().getName().toLowerCase().contains(x.toLowerCase()) || x.toLowerCase().contains(userAgent.getOperatingSystem().getName().toLowerCase());

        boolean mobileCheck = true, tabletCheck = true, desktopCheck = true, otherDeviceCheck = true, browserCheck = true, osCheck = true;

        //MOBILE CHECK
        if(!deviceFilterData.isAllowedMobile() && predicateMobile.test(deviceType))
            mobileCheck = false;
        //TABLET CHECK
        if(!deviceFilterData.isAllowedTablet() && predicateTablet.test(deviceType))
            tabletCheck = false;
        //DESKTOP CHECK
        if(!deviceFilterData.isAllowedDesktop() && predicateComputer.test(deviceType))
            desktopCheck = false;
        //OTHER DEVICE CHECK
        if(!deviceFilterData.isAllowedOther() && !(predicateMobile.test(deviceType) || predicateTablet.test(deviceType) || predicateComputer.test(deviceType)))
            otherDeviceCheck = false;

        //BROWSER CHECK
        if((!deviceFilterData.isAllowBlockBrowsers() && deviceFilterData.getBrowsers().stream().anyMatch(predicateBrowser))
            || (deviceFilterData.isAllowBlockBrowsers() && deviceFilterData.getBrowsers().stream().noneMatch(predicateBrowser)))
            browserCheck = false;

        //OS CHECK
        if((!deviceFilterData.isAllowBlockOs() && deviceFilterData.getOs().stream().anyMatch(predicateOs))
            || (deviceFilterData.isAllowBlockOs() && deviceFilterData.getOs().stream().noneMatch(predicateOs)))
            osCheck = false;

        Map<String, Boolean> deviceReport = new HashMap<String, Boolean>();
        deviceReport.put("MOBILE", mobileCheck);
        deviceReport.put("TABLET", tabletCheck);
        deviceReport.put("DESKTOP", desktopCheck);
        deviceReport.put("OTHERDEVICE", otherDeviceCheck);
        deviceReport.put("BROWSER", browserCheck);
        deviceReport.put("OS", osCheck);

        return deviceReport;
    }
}
