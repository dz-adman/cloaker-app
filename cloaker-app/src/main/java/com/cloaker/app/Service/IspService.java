package com.cloaker.app.Service;

import com.cloaker.app.POJO.FilterData.IspFilterData;
import com.cloaker.app.POJO.ThirdParty.Connection;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class IspService {

    public Map<String, Boolean> ispFilter(@NotNull IspFilterData ispFilterData, @NotNull Connection connection) {

        final Predicate<String> predicateIsp = x -> x.equalsIgnoreCase(connection.getIsp_name());
        final Predicate<String> predicateOrg = x -> x.equalsIgnoreCase(connection.getOrganization_name());

        boolean ispCheck = false;
        //BLOCKED ISP
        if((!ispFilterData.isAllowBlockIsp() && ispFilterData.getBlockedIspList().stream().noneMatch(predicateIsp.and(predicateOrg)))
            || (ispFilterData.isAllowBlockIsp() && ispFilterData.getBlockedIspList().stream().anyMatch(predicateIsp.and(predicateOrg))))
            ispCheck = true;

        Map<String, Boolean> ispReport = new HashMap<>();
        ispReport.put("ISP", ispCheck);

        return ispReport;
    }
}
