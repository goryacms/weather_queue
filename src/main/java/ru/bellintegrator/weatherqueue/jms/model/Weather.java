package ru.bellintegrator.weatherqueue.jms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.messaging.Message;
import ru.bellintegrator.weatherqueue.jms.view.ForecastView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Weather.class)
public class Weather implements Serializable {
    private int count;

    private String created;

    private String lang;

    private Wind wind;

    private List<Forecast> forecast = new ArrayList<>();


    public Weather() {
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

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

//    @Override
//    public String toString() {
//        return "Weather{" +
//                "count=" + getCount() +
//                ", created='" + getCreated() + '\'' +
//                ", lang='" + getLang() + '\'' +
//                ", wind=" + getWind() +
//                ", forecast=" + getForecast() +
//                '}';
//    }


    @Override
    public String toString(){
        JSONObject jsonInfo = new JSONObject();
        try {

            jsonInfo.put("count", this.count);
            jsonInfo.put("created", this.created);
            jsonInfo.put("lang", this.lang);
            jsonInfo.put("wind", this.wind);


            JSONArray forecastArray = new JSONArray();
            if (this.getForecast().size() > 0) {
                this.getForecast().forEach(forec -> {
                    JSONObject subJson = new JSONObject();
                    try {
                        subJson.put("code", forec.getCode());
                        subJson.put("date", forec.getDate());
                        subJson.put("day", forec.getDay());
                        subJson.put("high", forec.getHigh());
                        subJson.put("low", forec.getLow());
                        subJson.put("text", forec.getText());
                    } catch (JSONException e) {}

                    forecastArray.put(subJson);
                });
            }

            jsonInfo.put("forecast", forecastArray);
        } catch (JSONException e1) {}
        return jsonInfo.toString();
    }

}
