package com.applicanttest.event.service;

import com.applicanttest.event.bo.Event;
import com.applicanttest.event.bo.EventSummary;
import com.applicanttest.event.dao.EventDAO;
import com.applicanttest.event.domainvalues.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EventDataServiceImplTest {

    @Before
    public void cleanUpTableBeforeTests() {
        EventDAO eventDAO = new EventDAO();
        eventDAO.createTables();
    }

    @Test
    public void registerEventsTest() {
        EventDataServiceImpl eventDataService = new EventDataServiceImpl();
        List<Event> events = mockEventList();

        eventDataService.registerEvents(events);

        List<EventSummary> eventSummaryList = eventDataService.getEventList();

        assertEquals( eventSummaryList.get(0).getId(), events.get(0).getId());
        assertEquals( eventSummaryList.get(1).getId(), events.get(2).getId());

        assertFalse(eventSummaryList.get(0).isAlert());
        assertTrue(eventSummaryList.get(1).isAlert());

    }

    @After
    public void cleanUpTableAfterTests() {
        EventDAO eventDAO = new EventDAO();
        eventDAO.dropTable();
    }

    public List<Event> mockEventList(){

        Event event1 = new Event();
        Event event2 = new Event();
        Event event3 = new Event();
        Event event4 = new Event();

        event1.setId("srcaaabbb");
        event1.setTimeStamp(67867879L);
        event1.setType("APPLICATION_LOG");
        event1.setHost("4545");
        event1.setState(State.STARTED);

        event2.setId("srcaaabbb");
        event2.setTimeStamp(67867881L);
        event2.setType("APPLICATION_LOG");
        event2.setHost("4545");
        event2.setState(State.FINISHED);

        event3.setId("srccccddd");
        event3.setTimeStamp(67867888L);
        event3.setType("APPLICATION_LOG");
        event3.setHost("6868");
        event3.setState(State.STARTED);

        event4.setId("srccccddd");
        event4.setTimeStamp(67867894L);
        event4.setType("APPLICATION_LOG");
        event4.setHost("6868");
        event4.setState(State.FINISHED);

        return Arrays.asList(event1, event2, event3, event4);

    }
}
