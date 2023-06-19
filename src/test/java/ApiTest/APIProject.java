package ApiTest;

import Util.baseAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APIProject extends baseAPI {

    static String url = "https://api.todoist.com/rest/v2/projects";

    static String token;

    @Before
    public void init() {
        getUrl(url);
    }

    @Test
    public void accessToken() {
        String url = "https://todoist.com/API/v9/user/login";
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "nguyenthibich01092002@gmail.com");
        requestParams.put("password", "Miike0109");

        Response res = getToken(requestParams.toJSONString(), url);

        JsonPath jsonPathEvaluator = res.jsonPath();
        token = jsonPathEvaluator.get("token");
        System.out.println("token is : " + token);
    }

    @Test
    public void getAllProject() {
        getAPI(token, url);
    }

    @Test
    public void createProject() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Box 1");
        createAPI(token, requestParams.toJSONString());
    }

    @Test
    public void updateProject() {
        String id = "2314151671";
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Big Box");
        updateAPI(token, requestParams.toJSONString(), id);
    }

    @Test
    public void deleteProject() {
        String id = "2203306141";
        deleteAPI(token, id);
    }
}
