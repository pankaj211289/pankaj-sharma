package com.bestbuy.api_auto.stepdefs;

import com.bestbuy.api_auto.AssertUtil;
import com.bestbuy.api_auto.Util;
import com.bestbuy.api_auto.api.APIUser;
import io.cucumber.datatable.DataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for all the tests. This class contains all the common functions required to all tests
 *
 */
public abstract class AbstractBaseTest {

	private APIUser apiUser;
	protected AssertUtil assertUtil = new AssertUtil();
	
	/**
	 * Initializes {@link APIUser} class
	 * @return : {@link APIUser}
	 */
	protected APIUser api() {
		if(apiUser == null) {
			apiUser = new APIUser(Util.readApplicationData("baseUrl"));
		}
		apiUser.initAssertUtil();
		
		return apiUser;
	}

	protected Map<String, String> dataTableConverter(DataTable dataTable) {
		Map<String, String> mapTable = new HashMap<>();
		for(List<String> rows : dataTable.asLists(String.class)) {
			mapTable.put(rows.get(0), rows.get(1));
		}

		return mapTable;
	}
}
