package com.applicanttest.event.service;

import com.applicanttest.event.bo.Event;
import com.applicanttest.event.bo.EventSummary;

import java.util.List;

public interface IEventDataService {

    List<Event> readEventLogFile(String fileName);

    void registerEvents(List<Event> events);

    void initializeResources();

    void cleanResources();

    void listEvents();

    List<EventSummary> getEventList();

}
