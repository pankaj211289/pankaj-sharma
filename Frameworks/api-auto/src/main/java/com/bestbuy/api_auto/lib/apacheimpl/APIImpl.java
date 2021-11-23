package com.bestbuy.api_auto.lib.apacheimpl;

import java.io.IOException;

import com.bestbuy.api_auto.lib.IAPI;
import com.bestbuy.api_auto.lib.IResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class APIImpl implements IAPI {

	private CloseableHttpClient httpClient = HttpClients.createDefault();
	private String baseURI = "";
	
	public APIImpl(String baseURI) {
		this.baseURI = baseURI;
	}
	
	@Override
	public IResponse requestGet(String relativeURL) {
		try {
			return new ResponseImpl(httpClient.execute(new HttpGet(baseURI + relativeURL)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("GET request didn't execute");
	}

	@Override
	public IResponse requestPost(String relativeURL, String body) {
		throw new IllegalStateException("Not implemented yet");
	}

	@Override
	public IResponse requestPatch(String relativeURL, String body) {
		throw new IllegalStateException("Not implemented yet");
	}

	@Override
	public IResponse requestDelete(String relativeURL) {
		throw new IllegalStateException("Not implemented yet");
	}
}
