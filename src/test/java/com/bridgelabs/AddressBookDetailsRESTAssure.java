package com.bridgelabs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDetailsRESTAssure {
      @Before
      public void setUp()
      {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 4000;
      }

      public Response getAddressBookList()
      {
            Response response = RestAssured.get("/person/list/");
            return response;
      }

      @Test
      public void onCallingList_ReturnAddressBookList()
      {
            Response response = getAddressBookList();
            response.then().body("firstName", Matchers.hasItems("Saiprasad"));
      }

      @Test
      public void givenAddressBookDetails_OnPost_ShouldReturnAddedAddressBook()
      {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body("{\"firstName\": \"Anubhav\", \"lastName\": \"Singh\", \"address\": \"Hno 3\", " +
                            "\"cityName\": \"Patna\", \"stateName\": \"Bihar\", \"zipCode\": \"808080\"," +
                            "\"mobileNumber\": \"9293245345\"}")
                    .when()
                    .post("/person/create");
            response.then().body("id", Matchers.any(Integer.class));
            response.then().body("firstName", Matchers.is("Anubhav"));
      }

      @Test
      public void givenAddressBookDetails_OnUpdate_ShouldReturnUpdatedAddressBook()
      {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body("{\"firstName\": \"Harsh\", \"lastName\": \"Kurne\", \"address\": \"Hno 7\", " +
                            "\"city\": \"Hubli\", \"state\": \"Karnataka\", \"zipCode\": \"457885\"," +
                            "\"phone\": \"2453434523\"}")
                    .when()
                    .put("/person/update/4");
            response.then().body("id", Matchers.any(Integer.class));
            response.then().body("firstName", Matchers.is("Harsh"));
      }

      @Test
      public void givenAddressBookId_OnDelete_ShouldReturnSuccessStatus()
      {
            Response response = RestAssured.delete("/person/delete/4");
            int statusCode = response.getStatusCode();
            MatcherAssert.assertThat(statusCode, CoreMatchers.is(200));
            response = getAddressBookList();
            response.then().body("id", Matchers.not(Integer.class));
      }
}
