package com.cloaker.app.POJO.ThirdParty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class GeoLocation {

    private String ip_address;
    private String city;
    private String city_geoname_id;
    private String region;
    private String region_iso_code;
    private String region_geoname_id;
    private String postal_code;
    private String country;
    private String country_code;
    private String country_geoname_id;
    private String country_is_eu;
    private String continent;
    private String continent_code;
    private String continent_geoname_id;
    private double longitude;
    private double latitude;
    private Security security;
    private Timezone timezone;
    private Flag flag;
    private Currency currency;
    private Connection connection;

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_geoname_id() {
        return city_geoname_id;
    }

    public void setCity_geoname_id(String city_geoname_id) {
        this.city_geoname_id = city_geoname_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_iso_code() {
        return region_iso_code;
    }

    public void setRegion_iso_code(String region_iso_code) {
        this.region_iso_code = region_iso_code;
    }

    public String getRegion_geoname_id() {
        return region_geoname_id;
    }

    public void setRegion_geoname_id(String region_geoname_id) {
        this.region_geoname_id = region_geoname_id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_geoname_id() {
        return country_geoname_id;
    }

    public void setCountry_geoname_id(String country_geoname_id) {
        this.country_geoname_id = country_geoname_id;
    }

    public String getCountry_is_eu() {
        return country_is_eu;
    }

    public void setCountry_is_eu(String country_is_eu) {
        this.country_is_eu = country_is_eu;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public String getContinent_geoname_id() {
        return continent_geoname_id;
    }

    public void setContinent_geoname_id(String continent_geoname_id) {
        this.continent_geoname_id = continent_geoname_id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
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
