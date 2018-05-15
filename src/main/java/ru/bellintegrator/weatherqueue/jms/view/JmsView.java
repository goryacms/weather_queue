package ru.bellintegrator.weatherqueue.jms.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;


//@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = JmsDeserializer.class)
public class JmsView {
    private int count;

    private String created;

    private String lang;

    private LocationView location;

    private WindView wind;

    private AtmosphereView atmosphere;

    private List<ForecastView> forecast = new ArrayList<ForecastView>();

    public JmsView() {
    }

    public JmsView(int count, String created, String lang ) {
        this.count = count;
        this.created = created;
        this.lang = lang;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public LocationView getLocation() {
        return location;
    }

    public void setLocation(LocationView location) {
        this.location = location;
    }

    public WindView getWind() {
        return wind;
    }

    public void setWind(WindView wind) {
        this.wind = wind;
    }

    public AtmosphereView getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(AtmosphereView atmosphere) {
        this.atmosphere = atmosphere;
    }


    public List<ForecastView> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastView> forecast) {
        this.forecast = forecast;
    }

}