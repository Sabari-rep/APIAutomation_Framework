package api.testcase;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;

@Listeners(api.utilities.ExtentReportManager.class)
public class DDTests {
	
	public UserPayload userData;

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userId, String userName, String fname, String lname, String userEmali, String pwd, String phone) {
		
		//Required payload for POST request
		
		userData = new UserPayload();
		
		userData.setId(Integer.parseInt(userId));
		userData.setUsername(userName);
		userData.setFirstname(fname);
		userData.setLastname(lname);
		userData.setEmail(userEmali);
		userData.setPassword(pwd);
		userData.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userData);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String userName) {
		
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
