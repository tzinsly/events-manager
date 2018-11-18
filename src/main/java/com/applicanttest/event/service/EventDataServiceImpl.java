package com.applicanttest.event.service;

import com.applicanttest.event.bo.Event;
import com.applicanttest.event.bo.EventSummary;
import com.applicanttest.event.dao.EventDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventDataServiceImpl implements IEventDataService {

    final static Logger logger = Logger.getLogger(EventDataServiceImpl.class);

    @Override
    public List<Event> readEventLogFile(String fileName) {

        String line = null;
        List<Event> events = new ArrayList<>();
        try (FileReader fr = new FileReader(fileName);
             BufferedReader bfr = new BufferedReader(fr)) {

            while ((line = bfr.readLine()) != null) {

                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonObject object = (JsonObject) parser.parse(line);// response will be the json String
                Event evt = gson.fromJson(object, Event.class);
                events.add(evt);

            }

        } catch (FileNotFoundException e) {
            logger.error("Problem during file location: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Problem during file processing " + e.getMessage());
            e.printStackTrace();
        }

        return events;
    }


    @Override
    public void registerEvents(List<Event> events) {

        HashMap<String, Event> eventList = new HashMap<>();
        EventSummary eventSummary = new EventSummary();

        EventDAO eventDAO = new EventDAO();

        for (Event event : events) {

            if (eventList.containsKey(event.getId())) {
                eventSummary.setId(event.getId());
                eventSummary.setDuration(Math.abs(event.getTimeStamp() - eventList.get(event.getId()).getTimeStamp()));
                eventSummary.setType(event.getType());
                eventSummary.setHost(event.getHost());

                if (eventSummary.getDuration() > 4) {
                    eventSummary.setAlert(true);
                } else {
                    eventSummary.setAlert(false);
                }
                eventList.remove(event.getId());
                eventDAO.registerEvents(eventSummary);

            } else {
                eventList.put(event.getId(), event);
            }
        }

        if (eventList.size() > 0) {
            logger.warn("There is od number of events, one of the events has no records of START or FINISH");
        }

    }

    @Override
    public void initializeResources() {

        try {
            EventDAO eventDAO = new EventDAO();
            eventDAO.createTables();
        } catch (SQLException e ) {
            logger.warn("SQL Exception during creating table. Trying to cleanup resources... ");
            cleanResources();
        }

    }

    @Override
    public void cleanResources() {

        EventDAO eventDAO = new EventDAO();
        eventDAO.dropTable();

    }

    @Override
    public void listEvents() {

        EventDAO eventDAO = new EventDAO();
        eventDAO.listEvents();

    }

    @Override
    public List<EventSummary> getEventList() {

        List<EventSummary> eventList;

        EventDAO eventDAO = new EventDAO();
        eventList = eventDAO.getEventList();

        return eventList;

    }

}
