package com.applicanttest.event.dao;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class HsqldbConnectionTest {

    @Test
    public void  getConnectionTest () {

        assertNotNull(HsqldbConnection.getConnection());

    }
}
