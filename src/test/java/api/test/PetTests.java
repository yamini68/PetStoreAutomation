package api.test;

import api.endpoints.PetEndpoints;
import api.payload.petPayloads.Category;
import api.payload.petPayloads.Pet;
import api.payload.petPayloads.Tags;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class PetTests {

    Faker faker = new Faker();
    Pet Petpayload=new Pet();
    public static int PetId;



    @BeforeClass
    public void setupPetData(){
        PetId=faker.idNumber().hashCode();
        Petpayload.setId(PetId);
        Petpayload.setRandomData(faker.lorem().sentence());
        Petpayload.setName(faker.name().name());

        List<String>photourls=generateRandomUrls(3);

        Petpayload.setPhotoUrls(photourls);

        long categoryId=faker.number().randomNumber();
        String categoryname=faker.commerce().department();
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryname);

        Petpayload.setCategory(category);

        List<Tags> tagsList = new ArrayList<>();
        int numberOfTags = 3; // Set to however many tags you want

        for (int i = 0; i < numberOfTags; i++) {
            Tags tag = new Tags();
            tag.setId((int) faker.number().randomNumber());
            tag.setName(faker.lorem().word());
            tagsList.add(tag);
        }

// Setting tags to Petpayload
        Petpayload.setTags(tagsList);


    }


    public static List<String > generateRandomUrls(int count) {
        Faker faker = new Faker();
        List<String> urls = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            // Generate a random URL. Here, I'm using internet().url() as an example. You can use other Faker methods if needed.
            urls.add(faker.internet().url());
        }

        return urls;
    }

    @Test(priority = 1)
    public void UploadPetImageUsing_Post_Request(){
        Response response= PetEndpoints.UploadsImageOfPetUsingPost(this.Petpayload.getId(),this.Petpayload.getRandomData());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void AddNewPetToStoreUsing_Post_Request(){
        Response  response=PetEndpoints.AddNewPetToStoreUsingPost(Petpayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void UpdateTheExistingPetUsing_Put_Request(){
        Petpayload.setName(faker.name().name());
        List<String>photourls=generateRandomUrls(1);

        Petpayload.setPhotoUrls(photourls);
        List<Tags> tagsList = new ArrayList<>();
        int numberOfTags = 3; // Set to however many tags you want

        for (int i = 0; i < numberOfTags; i++) {
            Tags tag = new Tags();
            tag.setId((int) faker.number().randomNumber());
            tag.setName(faker.lorem().word());
            tagsList.add(tag);
        }

// Setting tags to Petpayload
        Petpayload.setTags(tagsList);

        Response response=PetEndpoints.UpdateTheExistingPetUsingPut(Petpayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test(priority = 4)
    public  void GetPetDetailsUsingStatus_GET_Request(){
        Response response=PetEndpoints.GetThePetByItsStatus();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 5)
    public void GetPetByID_Get_Request(){

        Response response=PetEndpoints.GetPetById(this.Petpayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 6)
    public void UpdatepetUsingData_Post_Request(){

        Response response=PetEndpoints.UpdatesApetUsingFormData_Post(this.Petpayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority =7)
    public void DeletingThePet(){

        Response response=PetEndpoints.Deletingthepet(this.Petpayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
