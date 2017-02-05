package com.cantekin.logger;


import org.junit.Before;
import org.junit.Test;


/**
 * Created by Cantekin on 5.2.2017.
 */
public class CustomLoggerTest {
    @Before
    public void setUp() throws Exception {
        CustomLogger.logType=null;
    }

    @Test
    public void error() throws Exception {
        CustomLogger.error("error","dsad");
    }

    @Test
    public void alert() throws Exception {
        CustomLogger.alert("alert","dsad");
    }

    @Test
    public void info() throws Exception {
        CustomLogger.info("info","dsad");
    }

}