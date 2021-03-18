package com.cloaker.app.Service;

import com.cloaker.app.POJO.ThirdParty.GeoLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class InitPOJO {

    @Value("${api_key}")
    String apiKey = null;
    private final OkHttpClient client = new OkHttpClient();
    public GeoLocation initIpLocationPOJOs(HttpServletRequest request) {

        String ipAddress = request.getRemoteAddr();
        String url = "https://ipgeolocation.abstractapi.com/v1/?api_key=" + apiKey + "&ip_address=" + ipAddress;
        try {
            Request req = new Request.Builder()
                    .url(url)
                    .get().build();

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(req).execute().body();
            GeoLocation geoLocation = objectMapper.readValue(responseBody.string(), GeoLocation.class);
            System.out.println(geoLocation);
            return geoLocation;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
