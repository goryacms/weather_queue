package ru.bellintegrator.weatherqueue.jms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.jms.core.JmsTemplate;

import org.springframework.messaging.support.MessageBuilder;

import org.springframework.stereotype.Service;

import ru.bellintegrator.weatherqueue.Application;
import ru.bellintegrator.weatherqueue.jms.dao.JmsDao;
import ru.bellintegrator.weatherqueue.jms.model.Forecast;
import ru.bellintegrator.weatherqueue.jms.model.Location;
import ru.bellintegrator.weatherqueue.jms.model.Wind;
import ru.bellintegrator.weatherqueue.jms.service.JmsService;
import ru.bellintegrator.weatherqueue.jms.view.ForecastView;
import ru.bellintegrator.weatherqueue.jms.view.WeatherView;
import ru.bellintegrator.weatherqueue.jms.model.Weather;

import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class JmsServiceImpl implements JmsService {
    private final JmsDao dao;
    private final ObjectMapper objMapper;
    private final JmsTemplate jmsTemplate;

    @Value("${outbound.endpoint}")
    private String destination;

    @Autowired
    public JmsServiceImpl(JmsDao dao, ObjectMapper objMapper, JmsTemplate jmsTemplate) {
        this.dao = dao;
        this.objMapper = objMapper;
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public Location loadByCity(String city) throws IOException {
        String resp = dao.loadByCity(city);

        WeatherView view = objMapper.readerFor(WeatherView.class).withRootName(Application.WEATHER_ROOT_NAME).readValue(resp);

        // Информация на конкретный город
        Location weatherInCity = new Location();

        weatherInCity.setCity(view.getLocation().getCity());
        weatherInCity.setCountry(view.getLocation().getCountry());
        weatherInCity.setRegion(view.getLocation().getRegion());

        // Информация о погоде (дата и т.д.)
        Weather weather = new Weather();

        weather.setCount(view.getCount());
        weather.setCreated(view.getCreated());
        weather.setLang(view.getLang());

        // Информация о ветре
        Wind wind = new Wind();
        wind.setChill(view.getWind().getChill());
        wind.setDirection(view.getWind().getDirection());
        wind.setSpeed(view.getWind().getSpeed());

        weather.setWind(wind);

        // Информация о прогнозах на несколько дней

        Function<ForecastView, Forecast> mapForecast = p -> {
            Forecast forecast = new Forecast();

            forecast.setCode(p.getCode());
            forecast.setDate(p.getDate());
            forecast.setDay(p.getDay());
            forecast.setHigh(p.getHigh());
            forecast.setLow(p.getLow());
            forecast.setText(p.getText());

            return forecast;
        };

        List forecastList = view.getForecast().stream()
                .map(mapForecast)
                .collect(Collectors.toList());

        weather.setForecast(forecastList);

        // Записываем на конкретный город
        weatherInCity.setWeather(weather);

        return weatherInCity;
    }

    @Override
    public void sendMessage(Location weatherInCity) {
        Message<Location> mes = MessageBuilder.withPayload(weatherInCity).build();
        jmsTemplate.convertAndSend(destination, mes);
    }
}