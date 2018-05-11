package ru.bellintegrator.weatherqueue.jms.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JmsView {
    //public String query;

    public int count;

    public String created;

    public String lang;

    public JmsView() {
    }
}