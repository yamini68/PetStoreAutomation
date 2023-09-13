package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = api.utilities.DataProviders.class)
    public void testPostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph){

        User UserPayload=new User();

        UserPayload.setId(Integer.parseInt(userID));
        UserPayload.setUsername(userName);
        UserPayload.setFirstName(fname);
        UserPayload.setLastName(lname);
        UserPayload.setEmail(useremail);
        UserPayload.setPassword(pwd);
        UserPayload.setPhone(ph);

        Response response= UserEndpoints.createUser(UserPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = api.utilities.DataProviders.class)
    public void testDeleteUserByUsername(String userName){
        Response response = UserEndpoints.deleteuser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
