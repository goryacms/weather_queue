package ru.bellintegrator.weatherqueue.jms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Forecast.class)
public class Forecast implements Serializable {
    private int code;

    private Date date;

    private String day;

    private int high;

    private int low;

    private String text;

    public Forecast() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("code", this.code);
            jsonInfo.put("date", this.date);
            jsonInfo.put("day", this.day);
            jsonInfo.put("high", this.high);
            jsonInfo.put("low", this.low);
            jsonInfo.put("text", this.text);
        } catch (JSONException e1) {}
        return jsonInfo.toString();
    }
}
