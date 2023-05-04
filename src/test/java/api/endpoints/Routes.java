package api.endpoints;

/*Swagger URI --> https://petstore.swagger.io/
Create user
https://petstore.swagger.io/v2/user
Get user
https://petstore.swagger.io/v2/user/{username}
update user
https://petstore.swagger.io/v2/user/{username}
Delete user
https://petstore.swagger.io/v2/user/{username}
	*/

public class Routes {
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User module
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";

	//Store module
	//here will create store module
	//pet module
	//here will create pet module
}
