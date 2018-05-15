package ru.bellintegrator.weatherqueue.jms.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import ru.bellintegrator.weatherqueue.util.Parsing;

import java.io.IOException;
import java.util.*;

public class JmsDeserializer extends StdDeserializer<JmsView> {
    private JmsView view;

    private LocationView locationView;

    private WindView windView;

    private AtmosphereView atmosphereView;

    private List<ForecastView> setForecast = new ArrayList<>();
    private ForecastView forecastView;

    public JmsDeserializer(){
        this(null);
    }
    public JmsDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public JmsView deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        // Определение нод
        JsonNode node    = jp.getCodec().readTree(jp);
        JsonNode channel = node.path("results").path("channel");

        JsonNode locationNode   = channel.path("location");
        JsonNode windNode       = channel.path("wind");
        JsonNode atmosphereNode = channel.path("atmosphere");
        //JsonNode forecastNode   = channel.path("item").path("forecast");
        JsonNode forecastNode = channel.path("item").get("forecast");




        // Получить объект JmaView
        view = getView(node);

        // Получить объект locationView
        setLocationView(locationNode);

        // Получить объект windView
        setWindView(windNode);

        // Получить объект atmosphereView
        setAtmosphereView(atmosphereNode);

        // Получить коллекцию объектов forecastView
        if (forecastNode.size() > 0) {
            setForecastCollection(forecastNode);
        }


        view.setLocation(locationView);
        view.setWind(windView);
        view.setAtmosphere(atmosphereView);
        view.setForecast(setForecast);

        return view;
    }


    private JmsView getView(JsonNode node) {
        int cnt = (Integer) ((IntNode) node.get("count")).numberValue();
        String itemCreated = node.get("created").asText();
        String lng = node.get("lang").asText();

        return new JmsView(cnt, itemCreated, lng);
    }

    private void setLocationView(JsonNode node){
        String city = node.get("city").asText();
        String country = node.get("country").asText();
        String region = node.get("region").asText();

        locationView =  new LocationView(city, country, region);
    }

    private void setWindView(JsonNode node) {
        int chill = (Integer) Integer.parseInt(node.get("chill").asText());
        int direction = (Integer) Integer.parseInt(node.get("direction").asText());
        int speed = (Integer) Integer.parseInt(node.get("speed").asText());

        windView = new WindView(chill, direction, speed);
    }

    private void setAtmosphereView(JsonNode node) {
        int humidity = Integer.parseInt(node.get("humidity").asText());
        Double pressure = Double.parseDouble(node.get("pressure").asText());
        int rising = Integer.parseInt(node.get("rising").asText());
        Double visibility = Double.parseDouble(node.get("visibility").asText());

        atmosphereView = new AtmosphereView(humidity, pressure, rising, visibility);
    }

    private void setForecastCollection(JsonNode node) {
        for (JsonNode forecastNode : node) {
            int code = Integer.parseInt(forecastNode.get("code").asText());
            String nodeDate = forecastNode.get("date").asText();
            Date outDate = Parsing.parseDate(nodeDate);

            String day = forecastNode.get("day").asText();
            int high = Integer.parseInt(forecastNode.get("high").asText());
            int low = Integer.parseInt(forecastNode.get("low").asText());
            String text = forecastNode.get("text").asText();

            forecastView = new ForecastView();

            forecastView.setCode(code);
            forecastView.setDate(outDate);
            forecastView.setDay(day);
            forecastView.setHigh(high);
            forecastView.setLow(low);
            forecastView.setText(text);

            setForecast.add(forecastView);
        }
    }

}
