package ru.bellintegrator.weatherqueue.jms.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bellintegrator.weatherqueue.Application;
import ru.bellintegrator.weatherqueue.jms.view.WeatherView;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@DirtiesContext
public class JmsDaoTest {

    @Autowired
    private JmsDao jmsDao;

    @Test
    public void loadByCity(){
        String city = "Пенза";

        String res = jmsDao.loadByCity(city);

        Assert.assertFalse(res.isEmpty());
    }

}