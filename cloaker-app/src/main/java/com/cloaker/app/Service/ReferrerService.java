package com.cloaker.app.Service;

import com.cloaker.app.POJO.FilterData.ReferrerFilterData;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class ReferrerService {

    public Map<String, Boolean> referrerFilter(@NotNull ReferrerFilterData referrerFilterData, String referer) {

        final Predicate<String> predicateReferrer = x -> x.equalsIgnoreCase(referer);

        boolean referrerCheck = false;
        if((referrerFilterData.isAllowBlockReferrer() && referrerFilterData.getReferrers().stream().anyMatch(predicateReferrer))
            || (!referrerFilterData.isAllowBlockReferrer() && referrerFilterData.getReferrers().stream().noneMatch(predicateReferrer)))
        referrerCheck = true;

        Map<String, Boolean> referefReport = new HashMap<>();
        referefReport.put("REFERER", referrerCheck);
        return referefReport;
    }

    public Request spoofReferrer(Request req) {
        // add header for referer with empty value (or dummy value maybe based on user choice)
        Request request = new Request.Builder()
                .url(req.url())
                .addHeader("referer", "dummyOrEmptyReferer")
                .get().build();
        return request;
    }
}
