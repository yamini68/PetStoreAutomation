package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import org.apache.logging.log4j.LogManager;


public class Usertests {

   Faker faker;
   User UserPayload;

    private static Logger logger = LogManager.getLogger(Usertests.class);


    @BeforeClass
    public void setupData(){

        faker=new Faker();
        UserPayload=new User();
        UserPayload.setId(faker.idNumber().hashCode());
        UserPayload.setUsername(faker.name().username());
        UserPayload.setFirstName(faker.name().firstName());
        UserPayload.setLastName(faker.name().lastName());
        UserPayload.setEmail(faker.internet().emailAddress());
        UserPayload.setPassword(faker.internet().password(5,10));
        UserPayload.setPhone(faker.phoneNumber().cellPhone());

        logger= LogManager.getLogger(this.getClass());

        logger.debug("debugging.....");

    }

    @Test(priority = 1)
    public void testPostUser(){

        logger.info("********** Creating user  ***************");

        Response response= UserEndpoints.createUser(UserPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("**********User is creatged  ***************");
    }

    @Test(priority = 2)
    public void testgetUserByUserName(){

        logger.info("********** Reading User Info ***************");

       Response response = UserEndpoints.Readuser(this.UserPayload.getUsername());
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);

        logger.info("**********User info  is displayed ***************");
    }

    @Test(priority = 3)
    public void testUpdateUserByUsername(){

        logger.info("********** Updating User ***************");

        UserPayload.setFirstName(faker.name().firstName());
        UserPayload.setLastName(faker.name().lastName());
        UserPayload.setEmail(faker.internet().emailAddress());
        Response response = UserEndpoints.updateUser(this.UserPayload.getUsername(),UserPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("********** User updated ***************");

        // checking wheather data is updated or not
        Response responseAfterUpdate = UserEndpoints.Readuser(this.UserPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);



    }

    @Test(priority = 4)
    public void testDeleteUserByUername(){

        logger.info("**********   Deleting User  ***************");

        Response response = UserEndpoints.deleteuser(this.UserPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("********** User deleted ***************");

    }

}
