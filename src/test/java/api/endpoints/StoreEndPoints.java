package api.endpoints;

import api.payload.Store;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreEndPoints {

    public static Response PlaceOrderForAPetUsingPost(Store payload){

        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body(payload)
                .when()
                .post(Routes.PlaceAnOrderForaPet_Post_Url);
        return response;
    }

    public static Response FindPurchaseOrderByIdUsingGet(int orderID){

        Response response=given()
                .header("accept","application/json")
                .pathParam("orderId",orderID)
                .when()
                .get(Routes.FindPurchaceOrderById_Get_Url);
        return response;
    }

    public static Response DeleteOrderById(int orderID){

        Response response=given()
                .header("accept","application/json")
                .pathParam("orderId",orderID)
                .when()
                .delete(Routes.DeleteOrderById);
        return response;
    }

    public static Response ProvidesTheAvaiableStockOfPets(){

        Response response=given()
                .header("accept","application/json")
                .when()
                .get(Routes.ReturnPetInventoriesByStatus_Get_Url);

        return response;
    }
}
