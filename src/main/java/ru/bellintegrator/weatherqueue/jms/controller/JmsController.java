package ru.bellintegrator.weatherqueue.jms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.bellintegrator.weatherqueue.jms.model.Location;
import ru.bellintegrator.weatherqueue.jms.service.JmsService;
import ru.bellintegrator.weatherqueue.jms.model.Weather;

import java.io.IOException;

@Controller
public class JmsController {
    private JmsService jmsService;

    @Autowired
    public JmsController(JmsService jmsService) {
        this.jmsService = jmsService;
    }

    @RequestMapping(value = "/jms", method = RequestMethod.POST)
    public String weatherData(Model model, @RequestBody String city) throws IOException {
        // Получить ответ с yahoo.weather.com
        Location weatherInCity = jmsService.loadByCity(city);


        // Сформировать JMS
        jmsService.sendMessage(weatherInCity);







        //Weather weather2 = new Weather();
        //jmsService.receiveMessage(weather2);

        return "redirect:/";
    }
}