package api.endpoints;

import api.payload.petPayloads.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetEndpoints {

    public static Response UploadsImageOfPetUsingPost(int petID, String catData) {
        String path = System.getProperty("user.dir") + "/Images/cat.jpg";
        File imageFile = new File(path);
        Response response = given()
                .header("accept", "application/json")
                .multiPart("file",  imageFile)
                .pathParam("petId", petID)
                .formParam("additionalMetadata", catData)
                .contentType(ContentType.MULTIPART)
                .when()
                .post(Routes.UploadImage_Post_url);
        return response;
    }

    public static Response AddNewPetToStoreUsingPost(Pet payload){

        Response response= given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body(payload)
                .when()
                .post(Routes.AddNewPetToStore_Post_url);
        return response;
    }

    public static Response UpdateTheExistingPetUsingPut(Pet payload){

        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body(payload)
                .when()
                .post(Routes.UpdateExitingPet_Put_url);
        return response;
    }

    public static Response GetThePetByItsStatus(){

        Response response=given()
                .header("accept","application/json")
                .queryParam("status","available")
                .when().get(Routes.GetPetByStatus_Get_url);
        return response;
    }

    public static Response GetPetById(int PetID){

        Response response=given()
                .header("accept","application/json")
                .pathParam("petId",PetID)
                .when().get(Routes.GetPetByID_Get_url);
        return response;
    }

    public static Response UpdatesApetUsingFormData_Post(int PetID){
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("name","dog")
                .formParam("status","pending")
                .pathParam("petId",PetID)
                .when()
                .post(Routes.UpdatePetWithData_Post_url);
        return response;
    }

    public static Response Deletingthepet(int petID){
        Response response=given()
                .header("accept","application/json")
                .header("'api_key","deleting the pet")
                .pathParam("petId",petID)
                .when()
                .delete(Routes.DeletePetWithID_delete_url);

        return response;
    }
}
