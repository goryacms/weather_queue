package ru.bellintegrator.weatherqueue.jms.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.bellintegrator.weatherqueue.util.Parsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JmsDeserializer extends StdDeserializer<WeatherView> {
    private WeatherView view;

    private LocationView location;

    private WindView wind;

    private List<ForecastView> setForecast = new ArrayList<>();

    public JmsDeserializer(){
        this(null);
    }
    public JmsDeserializer(Class<?> vc) {
        super(vc);
    }
    @Override
    public WeatherView deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        // Определение узлов
        JsonNode node    = jp.getCodec().readTree(jp);
        JsonNode channel = node.path("results").path("channel");

        JsonNode locationNode   = channel.path("location");
        JsonNode windNode       = channel.path("wind");

        //JsonNode forecastNode   = channel.path("item").path("forecast");
        JsonNode forecastNode = channel.path("item").get("forecast");

        // Получить объект JmsView
        view = getView(node);

        // Получить объект locationView
        setLocationView(locationNode);

        // Получить объект windView
        setWindView(windNode);

        // Получить коллекцию объектов forecastView
        if (forecastNode.size() > 0) {
            setForecastCollection(forecastNode);
        }


        view.setLocation(location);
        view.setWind(wind);
        view.setForecast(setForecast);

        return view;
    }


    private WeatherView getView(JsonNode node) {
        int cnt = (Integer) node.get("count").numberValue();
        String itemCreated = node.get("created").asText();
        String lng = node.get("lang").asText();

        return new WeatherView(cnt, itemCreated, lng);
    }

    private void setLocationView(JsonNode node){
        String city = node.get("city").asText();
        String country = node.get("country").asText();
        String region = node.get("region").asText();

        location =  new LocationView(city, country, region);
    }

    private void setWindView(JsonNode node) {
        int chill = Integer.parseInt(node.get("chill").asText());
        int direction = Integer.parseInt(node.get("direction").asText());
        int speed = Integer.parseInt(node.get("speed").asText());

        wind = new WindView(chill, direction, speed);
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

            ForecastView forecast = new ForecastView();

            forecast.setCode(code);
            forecast.setDate(outDate);
            forecast.setDay(day);
            forecast.setHigh(high);
            forecast.setLow(low);
            forecast.setText(text);

            setForecast.add(forecast);
        }
    }

}