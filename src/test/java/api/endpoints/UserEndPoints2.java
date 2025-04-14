package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This class file has been created to perform Create, Read, Update and Delete request for the User module API

public class UserEndPoints2 {

	// Method created for getting the URL's from properties files ***********
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // This method load the properties file
		return routes;
	}

	public static Response createUser(UserPayload payload) {

		String post_url = getURL().getString("post_url");   //This line get the URL from properties files

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(post_url);

		return response;

	}

	public static Response readUser(String userName) {

		String get_url = getURL().getString("get_url");  //This line get the URL from properties files

		Response response = given().pathParam("username", userName)

				.when().get(get_url);

		return response;

	}

	public static Response updateUser(String userName, UserPayload payload) {

		String update_url = getURL().getString("update_url");   //This line get the URL from properties files

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", userName)

				.when().put(update_url);

		return response;

	}

	public static Response deleteUser(String userName) {

		String delete_url = getURL().getString("delete_url");   //This line get the URL from properties files

		Response response = given().pathParam("username", userName)

				.when().delete(delete_url);

		return response;

	}

}
