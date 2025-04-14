package api.endpoints;

/*
 In ROUTES class i am maintaining only URI/URL of the API's
 
 Swagger URI/URL ===> https://petstore.swagger.io/
 https://petstore.swagger.io/v2/user
 
 1.Create User (POST) 	====> 		https://petstore.swagger.io/v2/user
 2.Get User (GET) 		====> 		https://petstore.swagger.io/v2/user/{username}
 3.Update User (PUT) 	====> 		https://petstore.swagger.io/v2/user/{username}
 4.Delete User(DELETE) 	====> 		https://petstore.swagger.io/v2/user/{username}
 */

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User Module HTTP Request URL's
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	

}
