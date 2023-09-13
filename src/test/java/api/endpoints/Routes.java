package api.endpoints;

/*

Base_url : petstore.swagger.io/v2
create user [ post ] :             petstore.swagger.io/v2/user
Get user by username [ Get ] :     petstore.swagger.io/v2/user/{username}
Update user [ put ] :              petstore.swagger.io/v2/user/{username}
Delete user [ Delete ] :           petstore.swagger.io/v2/user/{username}

 */
public class Routes {

    public static String base_url="https://petstore.swagger.io/v2";

    //User module

    public static String post_url=base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String put_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";

    /*
    pet module
     here you can  store all the urls which are related pet module
     */

    public static String UploadImage_Post_url=base_url+"/pet/{petId}/uploadImage";
    public static String AddNewPetToStore_Post_url=base_url+"/pet";
    public static String UpdateExitingPet_Put_url=base_url+"/pet";
    public static String GetPetByStatus_Get_url=base_url+"/pet/findByStatus";
    public static String GetPetByID_Get_url=base_url+"/pet/{petId}";
    public static String UpdatePetWithData_Post_url=base_url+"/pet/{petId}";
    public static String DeletePetWithID_delete_url=base_url+"/pet/{petId}";

    /*
    store module
     here you can  store all the urls which are related pet module
     */

    public static String PlaceAnOrderForaPet_Post_Url=base_url+"/store/order";
    public static String FindPurchaceOrderById_Get_Url=base_url+"/store/order/{orderId}";
    public static String DeleteOrderById=base_url+"/store/order/{orderId}";
    public static String ReturnPetInventoriesByStatus_Get_Url=base_url+"/store/inventory";




}
