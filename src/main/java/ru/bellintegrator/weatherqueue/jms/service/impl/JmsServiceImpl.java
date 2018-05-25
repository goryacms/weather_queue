package ru.bellintegrator.weatherqueue.jms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.Message;
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

import javax.jms.JMSException;
import javax.jms.Session;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        //objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

        System.out.println("Q = " + weatherInCity.getCity());

        return weatherInCity;
    }

    @Override
    public void sendMessage(Location weatherInCity) {
        //jmsTemplate.convertAndSend(Application.WEATHER_MESSAGE_QUEUE, weatherInCity);


        //jmsTemplate.convertAndSend("sampleQueue", "Hello world!");

        //jmsTemplate.convertAndSend("sampleQueue", "Hello world!");

        System.out.println(weatherInCity);
        jmsTemplate.convertAndSend(destination, weatherInCity);

//        jmsTemplate.send(destination, new MessageCreator() {
//            @Override
//            public javax.jms.Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(weatherInCity);
//            }
//        });
    }
}