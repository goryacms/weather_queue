package ru.bellintegrator.weatherqueue.jms.dao;

/**
 * Dao для загрузки информации с yahoo.weather.com
 */
public interface JmsDao {
    /**
     * Загрузчик данных по наименованию города
     * @param city
     * @return
     */
    String loadByCity(String city);
}
