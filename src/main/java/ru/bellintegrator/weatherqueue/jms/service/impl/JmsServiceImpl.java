package ru.bellintegrator.weatherqueue.jms.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherqueue.jms.dao.JmsDao;
import ru.bellintegrator.weatherqueue.jms.service.JmsService;
import ru.bellintegrator.weatherqueue.jms.view.JmsView;

import java.io.IOException;

@Service
public class JmsServiceImpl implements JmsService {
    private final JmsDao dao;
    private final ObjectMapper objMapper;

    @Autowired
    public JmsServiceImpl(JmsDao dao, ObjectMapper objMapper) {
        this.dao = dao;
        this.objMapper = objMapper;
    }

    @Override
    public JmsView loadByCity(String city) throws IOException {
        String resp = dao.loadByCity(city);

        //objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objMapper.readerFor(JmsView.class).withRootName("query").readValue(resp);
    }
}
