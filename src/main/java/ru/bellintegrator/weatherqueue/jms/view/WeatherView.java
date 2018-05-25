package ru.bellintegrator.weatherqueue.jms.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.bellintegrator.weatherqueue.jms.model.Wind;

import java.util.ArrayList;
import java.util.List;


//@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = JmsDeserializer.class)
public class WeatherView {
    private int count;

    private String created;

    private String lang;

    private LocationView location;

    private WindView wind;

    private List<ForecastView> forecast = new ArrayList<>();

    public WeatherView() {
    }

    public WeatherView(int count, String created, String lang ) {
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

    public List<ForecastView> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastView> forecast) {
        this.forecast = forecast;
    }


    @Override
    public String toString() {
        return "JmsView{" +
                "count=" + count +
                ", created='" + created + '\'' +
                ", lang='" + lang + '\'' +
                ", location=" + location +
                ", wind=" + wind +
                ", forecast=" + forecast +
                '}';
    }
}