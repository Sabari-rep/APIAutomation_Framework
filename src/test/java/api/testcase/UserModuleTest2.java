package api.testcase;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.UserPayload;
import io.restassured.response.Response;

@Listeners(api.utilities.ExtentReportManager.class)
public class UserModuleTest2 {   // This test is for getting the URL from properties file and running the test

	Faker faker;
	UserPayload userData;
	public Logger logger;  //For logs

	@BeforeClass()
	public void setUpData() {

		faker = new Faker();
		userData = new UserPayload();

		userData.setId(faker.idNumber().hashCode());
		userData.setUsername(faker.name().username());
		userData.setFirstname(faker.name().firstName());
		userData.setLastname(faker.name().lastName());
		userData.setEmail(faker.internet().safeEmailAddress());
		userData.setPassword(faker.internet().password(5, 10));
		userData.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());  //Logs

	}

	@Test(priority = 1)
	public void testPostUser() {

		// Test Validation
		logger.info("***** Creating User*******");
		
		Response response = UserEndPoints2.createUser(userData);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***** User is created*******");

	}

	@Test(priority = 2)
	public void testGetUserByName() {

		logger.info("***** Reading User*******");
		Response response = UserEndPoints2.readUser(this.userData.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***** User details displayed*******");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		// Updating User details by using User name
		
		logger.info("***** Updating User*******");

		userData.setEmail(faker.internet().safeEmailAddress());
		userData.setPhone(faker.phoneNumber().cellPhone());

		Response response = UserEndPoints2.updateUser(this.userData.getUsername(), userData);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("***** User details updated*******");
		// Validating the data after updating the user details
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userData.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}

	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("*****Deleting user*******");

		Response response = UserEndPoints2.deleteUser(this.userData.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***** User Deleted*******");

	}

}
