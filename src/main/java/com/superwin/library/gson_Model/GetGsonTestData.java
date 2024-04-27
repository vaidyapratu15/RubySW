package com.superwin.library.gson_Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class GetGsonTestData {

	public static Config getConfigData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//Config.json"));
		Config config = gson.fromJson(reader, Config.class);
		return config;
	}

	public static MultipleUserDetails getUserData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//MultipleUserDetails.json"));
		MultipleUserDetails userdata = gson.fromJson(reader, MultipleUserDetails.class);
		return userdata;
	}

	public static DbFields getDBData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//DbFields.json"));
		DbFields dbData = gson.fromJson(reader, DbFields.class);
		return dbData;
	}
	
	public static UserId getData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//UserId.json"));
		UserId UserId = gson.fromJson(reader, UserId.class);
		return UserId;
	}
	
	public static AccessToken getToken() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//AccessToken.json"));
		AccessToken token = gson.fromJson(reader, AccessToken.class);
		return token;
	}
	
	public static ReferalData getReferalData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//ReferalData.json"));
		ReferalData referalCode = gson.fromJson(reader, ReferalData.class);
		return referalCode;
	}
	
	public static NewUserData getNewUserData() throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "//ConfigurationFile//NewUserData.json"));
		NewUserData UserData = gson.fromJson(reader, NewUserData.class);
		return UserData;
	}

	public static void writeJson(String newUsername, String newPassword) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.remove("username");
		jsonObject.remove("password");

		jsonObject.put("username", newUsername);
		jsonObject.put("password", newPassword);

		try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "//ConfigurationFile//UserId.json")) {
			fileWriter.write(jsonObject.toString(4)); // Indent the JSON with 4 spaces for better readability
			fileWriter.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeAccessToken(String accessToken) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.remove("accessToken");

		jsonObject.put("accessToken", accessToken);
		
		try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "//ConfigurationFile//AccessToken.json")) {
			fileWriter.write(jsonObject.toString(4)); // Indent the JSON with 4 spaces for better readability
			fileWriter.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	public static void writeReferalData(String referalCode, String referalLink) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.remove("referalCode");
		jsonObject.remove("referalLink");

		jsonObject.put("referalCode", referalCode);
		jsonObject.put("referalLink", referalLink);
		
		try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "//ConfigurationFile//ReferalData.json")) {
			fileWriter.write(jsonObject.toString(4)); // Indent the JSON with 4 spaces for better readability
			fileWriter.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	public static void writeNewUserData(String username, String password, String query) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.remove("username");
		jsonObject.remove("password");
		jsonObject.remove("query");

		jsonObject.put("username", username);
		jsonObject.put("password", password);
		jsonObject.put("query", query);
		
		try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "//ConfigurationFile//NewUserData.json")) {
			fileWriter.write(jsonObject.toString(4)); // Indent the JSON with 4 spaces for better readability
			fileWriter.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
