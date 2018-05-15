package ru.bellintegrator.weatherqueue.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.weatherqueue.jms.service.JmsService;
import ru.bellintegrator.weatherqueue.jms.view.JmsView;


import java.io.IOException;



@RestController
@RequestMapping(value = "/api")
public class JmsController {
    private JmsService jmsService;

    @Autowired
    public JmsController(JmsService jmsService) {
        this.jmsService = jmsService;
    }


    @RequestMapping(value = "/jms", method = RequestMethod.POST)
    public JmsView weatherData(@RequestBody String city) throws IOException {
        JmsView view = jmsService.loadByCity(city);

        return view;
    }







}
