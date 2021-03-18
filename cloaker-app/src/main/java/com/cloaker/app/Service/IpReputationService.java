package com.cloaker.app.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IpReputationService {

    public Map<String, Boolean> checkIpRep() {
        //Check IP-Reputation
        Map<String, Boolean> ipRepReport = new HashMap<>();
        ipRepReport.put("IPREPUTATION", true);
        return ipRepReport;
    }
}
