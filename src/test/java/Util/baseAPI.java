package Util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class baseAPI {

    public void getUrl(String url) {
        System.out.println();
        RestAssured.baseURI = url;
    }

    public Response getToken(String requestParams, String url) {
        System.out.println("------------start get Token-------------");
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .body(requestParams)
                .post(url);
        res.prettyPrint();
        res.then().statusCode(200);
        return res;
    }

    public void getAPI(String token, String url) {
        try {
            System.out.println("------------start get API-------------");
            Response res = given()
                    .header("authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .when()
                    .get(url);
            res.prettyPrint();
            res.then().statusCode(200);
            System.out.println("------------get API success-------------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------------get API failed-------------");
        }
    }

    public Response createAPI(String token, String requestParams) {
        System.out.println("------------start create API-------------");
        Response res = given()
                .header("authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .body(requestParams)
                .post();
        res.prettyPrint();
        res.then().statusCode(200);
        System.out.println("------------create API success-------------");
        return res;
    }

    public void updateAPI(String token, String requestParams, String id) {
        try {
            System.out.println("------------start update API-------------");
            Response res = given()
                    .header("authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .when()
                    .body(requestParams)
                    .post("/" + id);
            res.prettyPrint();
            res.then().statusCode(200);
            System.out.println("------------update API success-------------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------------update API failed-------------");
        }

    }

    public void deleteAPI(String token, String id) {
        Response res = given()
                .header("authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete("/" + id);
        res.prettyPrint();
        res.then().statusCode(204);
    }

    public void reOpenTask(String token, String id){
        Response res = given()
                .header("authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .post("/" + id + "/reopen");
        res.prettyPrint();
        res.then().statusCode(204);
    }
}
