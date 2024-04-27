package com.superwin.library.gson_Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class GetGsonSportsTestData {

	public static BackendSportsConfig getBackendSportsConfigData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//BackendSportsConfig.json"));
		BackendSportsConfig config = gson.fromJson(reader, BackendSportsConfig.class);
		return config;
	}
	
	public static BookUserId getBookUserData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(
				new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//BookUserId.json"));
		BookUserId NewUserIdPwd = gson.fromJson(reader, BookUserId.class);
		return NewUserIdPwd;
	}

	public static void writeJson(String newUsername, String newPassword, String newMobileNo) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.remove("username");
		jsonObject.remove("password");
		jsonObject.remove("mobileNo");

		jsonObject.put("username", newUsername);
		jsonObject.put("password", newPassword);
		jsonObject.put("mobileNO", newMobileNo);

		try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "//ConfigurationFile//BookUserId.json")) {
			fileWriter.write(jsonObject.toString(4)); // Indent the JSON with 4 spaces for better readability
			fileWriter.flush();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
