package com.applicanttest.event.dao;

import com.applicanttest.event.bo.EventSummary;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    final static Logger logger = Logger.getLogger(EventDAO.class);

    public void createTables() throws SQLException {
        Connection conn = HsqldbConnection.getConnection();
        Statement stm = null;

        String query = "create table event(id varchar(50) not null, duration bigint not null, " +
                "type varchar(50), host varchar(50), alert boolean, PRIMARY KEY (id) ) ";


        stm = conn.createStatement();
        stm.executeUpdate(query);

    }

    public void registerEvents(EventSummary event) {
        Connection conn = HsqldbConnection.getConnection();
        int result = 0;

        String query = "insert into event(id, duration, type, host, alert) " +
                " values ( ?, ?, ?, ?, ? )";
        try (PreparedStatement stm = conn.prepareStatement(query);) {

            stm.setString(1, event.getId());
            stm.setLong(2, event.getDuration());
            stm.setString(3, event.getType());
            stm.setString(4, event.getHost());
            stm.setBoolean(5, event.isAlert());

            result = stm.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            logger.error("SQL Exception during Event Insertion, return code: " + result + " message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listEvents() {
        Connection conn = HsqldbConnection.getConnection();
        int result = 0;

        String query = "select * from event";
        try (PreparedStatement stm = conn.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                System.out.print("Id: " + rs.getString("id"));
                System.out.print(" Duration: " + rs.getLong("duration"));
                System.out.print(" Alert: " + rs.getBoolean("alert"));
                System.out.println();
            }


        } catch (SQLException e) {
            logger.error("SQL Exception during Event retrieve, return code: " + result + " message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<EventSummary> getEventList() {
        Connection conn = HsqldbConnection.getConnection();
        int result = 0;

        List<EventSummary> eventSummaryList = new ArrayList<>();
        EventSummary eventSummary = new EventSummary();
        String query = "select * from EVENT";


        try (PreparedStatement stm = conn.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                eventSummary.setId(rs.getString("id"));
                eventSummary.setDuration(rs.getLong("duration"));
                eventSummary.setAlert(rs.getBoolean("alert"));
                eventSummaryList.add(eventSummary);
                eventSummary = new EventSummary();
            }

        } catch (SQLException e) {
            logger.error("SQL Exception during Event List retrieve, return code: " + result + " message: " + e.getMessage());
            e.printStackTrace();
        }
        return eventSummaryList;
    }

    public void dropTable() {
        Connection conn = HsqldbConnection.getConnection();

        String query = "drop table event";


        try (PreparedStatement stm = conn.prepareStatement(query);) {
            stm.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL Exception during cleanup table " + e.getMessage());
            e.printStackTrace();
        }
    }

}
