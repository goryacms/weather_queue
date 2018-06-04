package ru.bellintegrator.weatherqueue.jms.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bellintegrator.weatherqueue.Application;
import ru.bellintegrator.weatherqueue.jms.model.Location;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@DirtiesContext
public class JmsServiceTest {
    @Autowired
    private JmsService jmsService;

    @Test
    public void loadByCity(){
        String city = "Пенза";


        Location res = null;
        try {
            res = jmsService.loadByCity(city);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert res != null;
        Assert.assertEquals("Penza", res.getCity());
    }
}