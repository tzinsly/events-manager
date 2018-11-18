package com.applicanttest.event.dao;

import com.applicanttest.event.bo.EventSummary;
import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class EventDAOTest {

    EventDAO eventDAO = new EventDAO();

    @Before
    public void createTableBeforeTests() throws SQLException {
        eventDAO.createTables();
    }

    @After
    public void cleanUpTableAfterTests() {
        eventDAO.dropTable();
    }

    @Test
    public void givenEventSummaryList_returnEventRegistrationSuccessfully() {

        //Mocking Data
        EventSummary eventSummary = new EventSummary();
        eventSummary.setId("scstttabc");
        eventSummary.setDuration(5);
        eventSummary.setHost("5678");
        eventSummary.setType("PROCESS_FILE");
        eventSummary.setAlert(true);

        eventDAO.registerEvents(eventSummary);

        assertEquals(eventSummary.getId(), eventDAO.getEventList().get(0).getId());

    }

    @Test
    public void getEventSummaryListTest() {

        EventDAO eventDAO = new EventDAO();

        //Mocking Data
        EventSummary eventSummary = new EventSummary();
        eventSummary.setId("scstttabc");
        eventSummary.setDuration(5);
        eventSummary.setHost("5678");
        eventSummary.setType("PROCESS_FILE");
        eventSummary.setAlert(true);

        eventDAO.registerEvents(eventSummary);
        eventDAO.getEventList();

        assertEquals(eventSummary.getId(), eventDAO.getEventList().get(0).getId());

    }



}
