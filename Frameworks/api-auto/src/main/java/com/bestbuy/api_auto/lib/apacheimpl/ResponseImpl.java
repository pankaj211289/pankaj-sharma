package com.bestbuy.api_auto.lib.apacheimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bestbuy.api_auto.Util;
import com.bestbuy.api_auto.lib.IResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;

public class ResponseImpl implements IResponse {

	private CloseableHttpResponse response;
	private String responseAsString;
	
	public ResponseImpl(CloseableHttpResponse response) {
		this.response = response;
		responseAsString = null;
	}
	
	@Override
	public void print() {
		Util.getLogger().info("Response Body: \n" + toString());
	}

	@Override
	public int getStatusCode() {
		return response.getStatusLine().getStatusCode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getResponseBodyAsList(String path) {
		throw new IllegalStateException("Not Implemented yet!");
	}

	@Override
	public <T> T getResponseBody(Class clazz) {
		throw new IllegalStateException("Not Implemented yet!");
	}

	@Override
	public <T> T getResponseBody(String path) {
		JsonObject obj = new Gson().fromJson(toString(), JsonObject.class);
		String[] seg = path.split("\\.");
		for (String element : seg) {
			if (obj != null) {
				JsonElement ele = obj.get(element);
				if (!ele.isJsonObject())
					return (T) ele;
				else
					obj = ele.getAsJsonObject();
			} else {
				return null;
			}
		}
		return (T) obj;
	}

	@Override
	public <T> Object getResponseBody(T[] t) {
		Gson gson = new Gson();
		return gson.fromJson(toString(), t.getClass());
	}
	
	public String toString() {
		if(responseAsString == null) {
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(
					this.response.getEntity().getContent()))) {
				String inputLine;
				StringBuffer response = new StringBuffer();
	
				while ((inputLine = reader.readLine()) != null) {
					response.append(inputLine);
				}
				responseAsString = response.toString();
				return responseAsString;
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			return responseAsString;
		}

		throw new IllegalStateException("Unabled to read response");
	}
}
