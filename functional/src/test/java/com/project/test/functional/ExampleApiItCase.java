package com.project.test.functional;


import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExampleApiITCase {

  @Test
  @DisplayName("Expect 200 status code when endpoint registered.")
  void callUndefinedRoute() {
    // @formatter:off
    given().
        port(9092).
        when().
        get("/api/example").
        then()
        .assertThat().
        statusCode(400);
    // @formatter:on
  }


}
