package com.bestbuy.api_auto.app;

import static org.hamcrest.Matchers.is;

import com.bestbuy.api_auto.api.APIUser;
import com.bestbuy.api_auto.api.StatusCode;

public class HealthCheckManager {
	private APIUser apiUser;
    private String URL = "/healthcheck";

    public HealthCheckManager(APIUser apiUser) {
        this.apiUser = apiUser;
        this.apiUser.setRelativeUrl(URL);
    }

    public void checkHealth() {
    	this.apiUser.setRelativeUrl(URL);
        this.apiUser
               .requestGet()
               .verify().responseCode(StatusCode.OK, "Response code while getting all products")
               .verify().responseBody("readonly", is(false), "Read only set to false");
    }

}
