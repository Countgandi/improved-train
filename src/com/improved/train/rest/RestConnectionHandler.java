package com.improved.train.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author Count
 *
 */
public class RestConnectionHandler {
	
	public static final String QUERY_URL = "https://www.dnd5eapi.co";
	
	public static HashMap<String, String> dataUrls = new HashMap<String, String>();	
	
	
	public static void startConnectionHandler() {
		JSONObject obj = new JSONObject(recieveConnection(QUERY_URL + "/api"));
		
		for(String yes: obj.keySet()) {
			dataUrls.put(yes, obj.getString(yes));
		}
	}
	
	public static JSONObject getObject(String index) {
		return new JSONObject(recieveConnection(QUERY_URL + dataUrls.get(index)));
	}
	
	public static JSONArray getArray(String index) {
		return new JSONArray(recieveConnection(QUERY_URL + dataUrls.get(index)));
	}
	
	public static JSONObject getObjectURL(String url) {
		return new JSONObject(recieveConnection(QUERY_URL + url));
	}
	
	public static JSONArray getArrayURL(String url) {
		return new JSONArray(recieveConnection(QUERY_URL + url));
	}
	
	private static String recieveConnection(String url) {
		String data = "";
		try {
			URL con = new URL(url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.openStream()));
			String s;
			while((s = reader.readLine()) != null) {
				data += s;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
}
