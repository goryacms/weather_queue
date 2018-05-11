package ru.bellintegrator.weatherqueue.jms.service;

import ru.bellintegrator.weatherqueue.jms.view.JmsView;

import java.io.IOException;

public interface JmsService {
    JmsView loadByCity(String city) throws IOException;
}
