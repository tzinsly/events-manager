package com.applicanttest.event.dao;

import com.applicanttest.event.bo.EventSummary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventDAOTest {

    @Before
    public void cleanUpTableBeforeTests() {
        EventDAO eventDAO = new EventDAO();
        eventDAO.dropTable();
        eventDAO.createTables();
    }

    @Test
    public void testEventRegistrationSuccessfully() {

        //Mocking Data
        EventSummary eventSummary = new EventSummary();
        eventSummary.setId("scstttabc");
        eventSummary.setDuration(5);
        eventSummary.setHost("5678");
        eventSummary.setType("PROCESS_FILE");
        eventSummary.setAlert(true);

        EventDAO eventDAO = new EventDAO();

        eventDAO.registerEvents(eventSummary);

        assertEquals(eventSummary.getId(), eventDAO.getEventList().get(0).getId());

    }

    @After
    public void cleanUpTableAfterTests() {
        EventDAO eventDAO = new EventDAO();
        eventDAO.dropTable();
    }
}
