package co.marcuss.interviews.resources;

import com.codahale.metrics.health.HealthCheck;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import co.marcuss.interviews.CalcServiceApplication;
import co.marcuss.interviews.CalcServiceConfiguration;
import co.marcuss.interviews.api.Response;
import io.dropwizard.testing.junit.DropwizardAppRule;

/**
 * Created by marcuss on 8/4/16.
 */
@RunWith(DataProviderRunner.class)
public class CalcServiceResourceTest {

  @ClassRule
  public static final DropwizardAppRule<CalcServiceConfiguration> RULE = new DropwizardAppRule<>(
      CalcServiceApplication.class);
  public static final String ADD_PATH = "/calc" + "/add";
  public static final String SUBTRACT_PATH = "/calc" + "/subtract";
  public static final String MULTIPLY_PATH = "/calc" + "/multiply";
  public static final String DIVIDE_PATH = "/calc" + "/divide";

  private Client client;

  @Before
  public void setUp() throws Exception {
    client = ClientBuilder.newClient();
  }

  @DataProvider
  public static Object[][] addData() {
    return new Object[][]{
        {"3/2/1", 6},
        {"-3/2/1", 0},
        {"-2/2/-2", -2},
        {"-2/-2", -4},
        {"2", 2},
        {"-2/-2/-2/-2/-2", -10}
    };
  }

  @Test
  @UseDataProvider("addData")
  public void testAdd(String numbers, Integer result) throws Exception {
    final Response response = client.target("http://localhost:" + RULE.getLocalPort() + ADD_PATH)
        .path(numbers)
        .request()
          .get(Response.class);
    MatcherAssert.assertThat(response.result, CoreMatchers.equalTo(result.doubleValue()));
  }

  @DataProvider
  public static Object[][] subtractData() {
    return new Object[][]{
        {"3/2/1", 0},
        {"-3/2/1", -6},
        {"-2/2/-2", -2},
        {"-2/-2", 0},
        {"2", 2},
        {"-2/-2/-2/-2/-2", 6},
        {"-2/-2/-2/-2/-2/-2/-2/-2/-2/-2/-2/-2", 20}
    };
  }

  @Test
  @UseDataProvider("subtractData")
  public void testSubtract(String numbers, Integer result) throws Exception {
    final Response response = client.target("http://localhost:" + RULE.getLocalPort() + SUBTRACT_PATH)
        .path(numbers)
        .request()
        .get(Response.class);
    MatcherAssert.assertThat(response.result, CoreMatchers.equalTo(result.doubleValue()));
  }

  @DataProvider
  public static Object[][] multiplyData() {
    return new Object[][]{
        {"3/2/1", 6},
        {"-3/2/1", -6},
        {"-2/2/-2", 8},
        {"-2/-2", 4},
        {"-2/-2/-2/-2/-2", -32},
        {"2", 2}
    };
  }

  @Test
  @UseDataProvider("multiplyData")
  public void testMultiply(String numbers, Integer result) throws Exception {
    final Response response = client.target("http://localhost:" + RULE.getLocalPort() + MULTIPLY_PATH)
        .path(numbers)
        .request()
        .get(Response.class);
    MatcherAssert.assertThat(response.result, CoreMatchers.equalTo(result.doubleValue()));
  }

  @Test
  public void testDivide() throws Exception {
    final Response response = client.target("http://localhost:" + RULE.getLocalPort() + DIVIDE_PATH)
        .path("/5/2")
        .request()
        .get(Response.class);
    MatcherAssert.assertThat(response.result, CoreMatchers.equalTo(2.5d));
  }

  @DataProvider
  public static Object[][] paths() {
    return new Object[][]{
        {ADD_PATH},
        {SUBTRACT_PATH},
        {DIVIDE_PATH},
        {MULTIPLY_PATH}
    };
  }

  @Test(expected = BadRequestException.class)
  @UseDataProvider("paths")
  public void testNotANumber(String path) throws Exception {
    client.target("http://localhost:" + RULE.getLocalPort() + path)
        .path("/Two/One/B")
        .request()
        .get(Response.class);
  }

  @Test
  @Ignore
  public void testHealthCheck() throws Exception {
    System.out.println(
    client.target("http://localhost:" + RULE.getLocalPort() + "/healthchecks")
        .request()
        .get(HealthCheck.Result.class));
  }

  @DataProvider
  public static Object[][] jsonResponse() {
    return new Object[][]{
        {"/2/2/2", ADD_PATH, "{\"result\":6.0}"},
        {"/2/2/2", SUBTRACT_PATH, "{\"result\":-2.0}"},
        {"/5/2", DIVIDE_PATH, "{\"result\":2.5}"},
        {"/2/2/2", MULTIPLY_PATH, "{\"result\":8.0}"},
        {"/2/2/2/2/2/2/2/2", MULTIPLY_PATH, "{\"result\":256.0}"}
    };
  }

  @Test
  @UseDataProvider("jsonResponse")
  public void testJsonFormat(String values, String path, String result) throws Exception {
    MatcherAssert.assertThat(
    client.target("http://localhost:" + RULE.getLocalPort() + path)
        .path(values)
        .request()
        .get(String.class),
        CoreMatchers.equalTo(result));
  }
}
