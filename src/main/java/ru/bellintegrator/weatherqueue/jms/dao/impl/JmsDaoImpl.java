package ru.bellintegrator.weatherqueue.jms.dao.impl;

import org.springframework.stereotype.Repository;
import org.yql4j.*;
import ru.bellintegrator.weatherqueue.jms.dao.JmsDao;

@Repository
public class JmsDaoImpl implements JmsDao {

    @Override
    public String loadByCity(String city) {
        YqlClient client = YqlClients.createDefault();
        YqlQuery query = YqlQueryBuilder
                .fromQueryString("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=@name)")
                .withVariable("name", city).withFormat(ResultFormat.JSON).build();

        YqlResult result = null;

        try {
            result = client.query(query);
        } catch (YqlException e) {
            e.printStackTrace();
        }

        String returnResult = null;
        if (result != null) {
            returnResult = result.getContentAsString();
        }

        return returnResult;
    }
}