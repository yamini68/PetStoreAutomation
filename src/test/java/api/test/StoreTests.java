package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import api.payload.petPayloads.Pet;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class StoreTests {

    Faker faker = new Faker();
    Store storepayload =new Store();


    @BeforeClass
    public void setupPetData(){
        storepayload.setId(faker.idNumber().hashCode());
        storepayload.setPetId(faker.number().randomNumber());
        storepayload.setQuantity(faker.number().numberBetween(1, 10));

        storepayload.setComplete(faker.bool().bool());
        storepayload.setStatus(faker.options().option("placed", "shipped", "delivered"));

    }

    @Test(priority = 1)
    public void PlaceAnOrderForThePetUsing_Post_Request(){
        Response response= StoreEndPoints.PlaceOrderForAPetUsingPost(storepayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 2)
    public void FindPurchaseOrderById_Get_Request(){

        Response response=StoreEndPoints.FindPurchaseOrderByIdUsingGet(this.storepayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void DeleteOrder(){

        Response response=StoreEndPoints.DeleteOrderById(this.storepayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 4)
    public void givesPetInventoriesUing_Get_Request(){

        Response response=StoreEndPoints.ProvidesTheAvaiableStockOfPets();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }



}
