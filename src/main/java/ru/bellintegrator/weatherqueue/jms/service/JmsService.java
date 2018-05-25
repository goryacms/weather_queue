package ru.bellintegrator.weatherqueue.jms.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import ru.bellintegrator.weatherqueue.jms.model.Location;
import ru.bellintegrator.weatherqueue.jms.model.Weather;

import javax.jms.Session;
import java.io.IOException;

public interface JmsService {
    Location loadByCity(String city) throws IOException;

    void sendMessage(Location weatherInCity);
}