package com.cloaker.app.Service;

import com.cloaker.app.POJO.FilterData.LocationFilterData;
import com.cloaker.app.POJO.ThirdParty.GeoLocation;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class LocationService {

    public Map<String, Boolean> locationFilter(@NotNull LocationFilterData locationFilterData, @NotNull GeoLocation geoLocation) {

        final Predicate<String> predicateCountry = x -> x.equalsIgnoreCase(geoLocation.getCountry());
        final Predicate<String> predicateRegion = x -> x.equalsIgnoreCase(geoLocation.getRegion());
        final Predicate<String> predicateCity = x -> x.equalsIgnoreCase(geoLocation.getCity());
        final Predicate<String> predicateContinent = x -> x.equalsIgnoreCase(geoLocation.getContinent());

        boolean countryCheck = true, stateCheck = true, cityCheck = true, continentCheck = true;

        // COUNTRY CHECK
        if((!locationFilterData.isAllowBlockCountry() && locationFilterData.getCountries().stream().noneMatch(predicateCountry))
            || (locationFilterData.isAllowBlockCountry() && locationFilterData.getCountries().stream().anyMatch(predicateCountry)))
            countryCheck = true;
        else countryCheck = false;
        // STATE CHECK
        if((!locationFilterData.isAllowBlockState() && locationFilterData.getStates().stream().noneMatch(predicateRegion))
            || (locationFilterData.isAllowBlockState() && locationFilterData.getStates().stream().anyMatch(predicateRegion)))
            stateCheck = true;
        else stateCheck = false;
        // CITY CHECK
        if((!locationFilterData.isAllowBlockCity() && locationFilterData.getCities().stream().noneMatch(predicateCity))
            || (locationFilterData.isAllowBlockCity() && locationFilterData.getCities().stream().anyMatch(predicateCity)))
            cityCheck = true;
        else cityCheck = false;

        // CONTINENT CHECK
        if((!locationFilterData.isAllowBlockContinent() && locationFilterData.getContinents().stream().noneMatch(predicateContinent))
            || (locationFilterData.isAllowBlockContinent() && locationFilterData.getContinents().stream().anyMatch(predicateContinent)))
            continentCheck = true;
        else continentCheck = false;

        Map<String, Boolean> locationReport = new HashMap<>();
        locationReport.put("COUNTRY", countryCheck);
        locationReport.put("STATE", stateCheck);
        locationReport.put("CITY", cityCheck);
        locationReport.put("CONTINENT", continentCheck);

        return locationReport;
    }

    public Map<String, Boolean> advancedLocationFilter(@NotNull LocationFilterData locationFilterData, @NotNull GeoLocation geoLocation) {
        Map<String, Boolean> advLocReport = new HashMap<>();
        advLocReport.put("ADVANCEDLOCATION",
                (geoLocation.getCountry() != null || !geoLocation.getCountry().equals(""))
                && (geoLocation.getCountry_code() != null || !geoLocation.getCountry_code().equals(""))
                && (geoLocation.getRegion() != null || !geoLocation.getRegion().equals(""))
                && (geoLocation.getRegion_iso_code() != null || !geoLocation.getRegion_iso_code().equals(""))
                && (geoLocation.getRegion_geoname_id() != null || !geoLocation.getRegion_geoname_id().equals(""))
                && (geoLocation.getCity() != null || !geoLocation.getCity().equals(""))
                && (geoLocation.getContinent() != null || !geoLocation.getContinent().equals(""))
                && (geoLocation.getPostal_code() != null || !geoLocation.getPostal_code().equals(""))
                && !String.valueOf(geoLocation.getLongitude()).equals("")
                && !String.valueOf(geoLocation.getLatitude()).equals("")
                && (geoLocation.getTimezone().getName() != null || !geoLocation.getTimezone().getName().equals(""))
                && (geoLocation.getTimezone().getAbbreviation() != null || !geoLocation.getTimezone().getAbbreviation().equals(""))
                && (geoLocation.getCurrency().getCurrency_name() != null || !geoLocation.getCurrency().getCurrency_name().equals(""))
                && (geoLocation.getCurrency().getCurrency_code() != null || !geoLocation.getCurrency().getCurrency_code().equals(""))
                && (geoLocation.getConnection().getIsp_name() != null || !geoLocation.getConnection().getIsp_name().equals(""))
                && (geoLocation.getConnection().getConnection_type() != null || !geoLocation.getConnection().getConnection_type().equals(""))
        );
        return advLocReport;
    }
}
