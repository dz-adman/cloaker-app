package com.cloaker.app.Service;

import com.cloaker.app.POJO.FilterData.ProxyVpnFilterData;
import com.cloaker.app.POJO.ThirdParty.Security;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProxyVpnService {

    public Map<String, Boolean> proxyVpnFilter(@NotNull ProxyVpnFilterData proxyVpnFilterData, @NotNull Security security) {

        Map<String, Boolean> proxyVpnReport = new HashMap<>();

        if(proxyVpnFilterData.isProxyVpnAllowed())
            proxyVpnReport.put("PROXYVPN", true);
        if(!proxyVpnFilterData.isProxyVpnAllowed())
            proxyVpnReport.put("PROXYVPN", !security.isIs_vpn());

        return proxyVpnReport;
    }
}
