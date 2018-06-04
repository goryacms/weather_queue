package ru.bellintegrator.weatherqueue.jms.service;

import ru.bellintegrator.weatherqueue.jms.model.Location;
import java.io.IOException;

/**
 * Бизнес-логика для работы с сообщениями
 */
public interface JmsService {
    /**
     * Загрузка информации о погоде по названию города
     * @param city
     * @return
     * @throws IOException
     */
    Location loadByCity(String city) throws IOException;

    /**
     * Отправка сообщения в topic
     * @param weatherInCity
     */
    void sendMessage(Location weatherInCity);
}