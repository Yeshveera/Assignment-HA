/*
 * Copyright 2016, Charter Communications, All rights reserved.
 */
package org.Assignment.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertNotNull;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author yashveera
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestTest {
        
    public static final String API_KEY_HEADER = "X-Api-Key";
    public static final String API_KEY_VALUE = "XfZJKTFdqFR9rSpvttyVV9UwotEV5ZmjwV5lwPpf";
    public static String stationId ="";
    
    @Before
    public void setUp() {
        
        RestAssured.baseURI = "https://api.data.gov";
        RestAssured.basePath = "/nrel/alt-fuel-stations/v1";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    @Test
    public void test1() {
        String id =
        given()
            .header(API_KEY_HEADER, API_KEY_VALUE)
            .accept(ContentType.JSON)
        .and()
            .queryParam("location", "Austin TX")
            .queryParam("ev_network", "ChargePoint Network")
        .when()
            .get("nearest.json")
        .then()
            .statusCode(200)
        .and()
            .body("fuel_stations.station_name", hasItem("HYATT AUSTIN"))
        .extract()
            .jsonPath()
            .getString("fuel_stations.find{it.station_name == 'HYATT AUSTIN'}.id");
        
        assertNotNull("Id cannot be blank", id);
        stationId = id;
    }
    
    @Test
    public void test2() {
        
        given()
            .header(API_KEY_HEADER, API_KEY_VALUE)
            .accept(ContentType.JSON)
        .when()
            .get(stationId + ".json")
        .then()
            .statusCode(200)
        .and()
            .body("alt_fuel_station.street_address", equalTo("208 Barton Springs Rd"));
    }
}
