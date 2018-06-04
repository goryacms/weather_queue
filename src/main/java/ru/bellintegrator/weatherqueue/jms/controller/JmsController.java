package ru.bellintegrator.weatherqueue.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.weatherqueue.jms.model.Location;
import ru.bellintegrator.weatherqueue.jms.service.JmsService;
import java.io.IOException;

@Controller
public class JmsController {
    private JmsService jmsService;

    @Autowired
    public JmsController(JmsService jmsService) {
        this.jmsService = jmsService;
    }

    public JmsController() {
    }

    /**
     * Метод для формирования и отправки jms-сообщения. Для получения параметров из формы используется POST
     * @param model
     * @param city
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/jms")
    public String weatherData(Model model, @RequestBody String city) throws IOException {
        // Получить ответ с yahoo.weather.com
        Location weatherInCity = jmsService.loadByCity(city);

        // Сформировать JMS
        jmsService.sendMessage(weatherInCity);

        return "redirect:/";
    }
}