///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Frontend;
//
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import java.util.List;
//import static org.hamcrest.CoreMatchers.is;
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Steps {
//
//    static WebDriver driver;
//    static String url = "http://localhost:7777/searchByCity.html";
//    Thread t;
//    String cityName;
//
//    @Given("^A city name is \"(.*)\"$")
//    public void cityName(String city) {
//        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.get(url);
//        cityName = city;
//    }
//
//    @When("^I type in the city name")
//    public void enterCity() {
//        WebElement input = driver.findElement(By.id("cityInput"));
//        input.sendKeys(cityName);
//        input.sendKeys(Keys.TAB);
//        try {
//            Thread.sleep(2000);
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//
//    @Then("^I should get (\\d+) results$")
//    public void i_should_get(int arg1) {
//        WebElement table = driver.findElement(By.tagName("tbody"));
//        List<WebElement> rows = table.findElements(By.tagName("tr"));
//        Assert.assertThat(rows.size(), is(arg1));
//    }
//
//    @And("^I should get error message \"([^\"]*)\"$")
//    public void i_should_get_error(String arg1) {
//        WebElement err = driver.findElement(By.id("errorText"));
//        Assert.assertThat(err.getText(), is(arg1));
//        driver.quit();
//    }
//}