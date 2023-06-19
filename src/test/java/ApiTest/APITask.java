package ApiTest;

import Util.baseAPI;
import Util.baseWeb;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITask extends baseAPI {

    static String token;

    static String url = "https://api.todoist.com/rest/v2/tasks";

    static String idTask;
    static String idProject;

    static baseWeb baseWeb;

    @Before
    public void init() {
        baseWeb = new baseWeb();
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
    public void createProject() {
        RestAssured.baseURI = "https://api.todoist.com/rest/v2/projects";
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Box 1");
        Response res = createAPI(token, requestParams.toJSONString());

        JsonPath jsonPathEvaluator = res.jsonPath();
        idProject = jsonPathEvaluator.get("id");
    }

    @Test
    public void createTask() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("content", "todo");
        requestParams.put("due_string", "tomorrow at 12:00");
        requestParams.put("due_lang", "en");
        requestParams.put("priority", "4");
        requestParams.put("project_id", idProject);

        Response res = createAPI(token, requestParams.toJSONString());
        JsonPath jsonPathEvaluator = res.jsonPath();
        idTask = jsonPathEvaluator.get("id");
        idProject = jsonPathEvaluator.get("project_id");
    }

    public void getURL() {
        String urlBrowser = "https://todoist.com/app/project/" + idProject;
        baseWeb.open_Browser("Chrome");
        baseWeb.getURL(urlBrowser);
    }

    public void login() {
        String email = "//*[@id='element-0']";
        String emailKey = "nguyenthibich01092002@gmail.com";
        String password = "//*[@id='element-3']";
        String passwordKey = "Miike0109";
        String btnLogin = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/button[1]";

        try {
            System.out.println("----------start login------------");
            baseWeb.waitForElement("xpath", email, 10, 5);
            baseWeb.send_key("xpath", email, emailKey);
            baseWeb.waitForElement("xpath", password, 10, 5);
            baseWeb.send_key("xpath", password, passwordKey);
            baseWeb.click_btn("xpath", btnLogin);
            System.out.println("----------login success------------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------login failed------------");
        }
    }

    @Test
    public void verifyTask() {
        getURL();
        login();
        String findTask = "//li[@id='task-" + idTask + "'" + "]";
        baseWeb.waitForElement("xpath", findTask, 30, 30);
        Boolean isTask = baseWeb.isElementPresent(idTask, "xpath", findTask);
        Assert.assertEquals(true, isTask);
        closecheckbox();
    }

    public void closecheckbox() {
        String closeTask = "//*[@id='task-" + idTask + "']//button[@aria-label='Mark task as complete']";
        baseWeb.click_btn("xpath", closeTask);
        baseWeb.waitForElement("xpath", closeTask, 45, 45);
        baseWeb.reload();
        Boolean isCloseTask = baseWeb.isElementPresent(idTask, "xpath", closeTask);
        Assert.assertEquals(false, isCloseTask);
    }

    @Test
    public void zTask() {
        String url = idTask;
        reOpenTask(token, url);

    }

    @After
    public void close() {
        baseWeb.close_Browser();
    }

}
