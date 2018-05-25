package ru.bellintegrator.weatherqueue.jms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Wind.class)
public class Wind implements Serializable {
    private int chill;

    private int direction;

    private int speed;

    public Wind() {
    }

    public int getChill() {
        return chill;
    }

    public void setChill(int chill) {
        this.chill = chill;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("chill", this.chill);
            jsonInfo.put("direction", this.direction);
            jsonInfo.put("speed", this.speed);
        } catch (JSONException e1) {}
        return jsonInfo.toString();
    }
}
