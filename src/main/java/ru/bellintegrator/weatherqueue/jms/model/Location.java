package ru.bellintegrator.weatherqueue.jms.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope = Location.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Location implements Serializable {
    private String city;

    private String country;

    private String region;

    private Weather weather;

    public Location() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString(){
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("city", this.city);
            jsonInfo.put("country", this.country);
            jsonInfo.put("region", this.region);
            jsonInfo.put("weather", this.weather);
        } catch (JSONException e1) {}
        return jsonInfo.toString();
    }
}