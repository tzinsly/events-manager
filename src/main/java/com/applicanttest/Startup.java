package com.applicanttest;

import com.applicanttest.event.bo.Event;
import com.applicanttest.event.service.EventDataServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class Startup {

    final static Logger logger = Logger.getLogger(Startup.class);

    public static void main(String[] args) {

        EventDataServiceImpl evntDS = new EventDataServiceImpl();

        logger.info("Initializing application Resources");
        evntDS.cleanResources();
        evntDS.initializeResources();

        logger.info("Reading file resource");
        List<Event> events = evntDS.readEventLogFile("C:\\Users\\TATIANEZINSLY\\Documents\\mine\\opt-conf\\dev\\events-manager\\src\\main\\resources\\events-log");

        logger.info("Registering all the events");
        evntDS.registerEvents(events);

        evntDS.listEvents();

    }
}
