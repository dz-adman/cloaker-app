package com.cloaker.app.POJO.FilterData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class LocationFilterData implements Serializable {

    private static final long serialVersionUID = -531845946502052699L;
    private boolean allowBlockCountry;
    private List<String> countries;
    private boolean allowBlockState;
    private List<String> states;
    private boolean allowBlockCity;
    private List<String> cities;
    private boolean allowBlockContinent;
    private List<String> continents;
    private boolean applyAdvancedFilter;

    public boolean isAllowBlockCountry() {
        return allowBlockCountry;
    }

    public void setAllowBlockCountry(boolean allowBlockCountry) {
        this.allowBlockCountry = allowBlockCountry;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public boolean isAllowBlockState() {
        return allowBlockState;
    }

    public void setAllowBlockState(boolean allowBlockState) {
        this.allowBlockState = allowBlockState;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public boolean isAllowBlockCity() {
        return allowBlockCity;
    }

    public void setAllowBlockCity(boolean allowBlockCity) {
        this.allowBlockCity = allowBlockCity;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public boolean isAllowBlockContinent() {
        return allowBlockContinent;
    }

    public void setAllowBlockContinent(boolean allowBlockContinent) {
        this.allowBlockContinent = allowBlockContinent;
    }

    public List<String> getContinents() {
        return continents;
    }

    public void setContinents(List<String> continents) {
        this.continents = continents;
    }

    public boolean isApplyAdvancedFilter() {
        return applyAdvancedFilter;
    }

    public void setApplyAdvancedFilter(boolean applyAdvancedFilter) {
        this.applyAdvancedFilter = applyAdvancedFilter;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
