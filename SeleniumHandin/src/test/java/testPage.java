/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ehn19
 */
public class testPage {

    static WebDriver driver;
    static String url = "http://localhost:3000/";

    public testPage() {
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
    }

    @Test
    public void test1() {
        System.out.println("Test 1. Verify that the DOM has loaded. "
                + "This can be done by checking that all 5 rows in the table"
                + "exists");
        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            WebElement e = d.findElement(By.tagName("tbody"));
            List<WebElement> rows = e.findElements(By.tagName("tr"));
            Assert.assertThat(rows.size(), is(5));
            return true;
        });

    }

    /*
    ** Der er ikke brug for en wait metode her, da det gøres i forrige test.
    ** Derved er vi sikre på at vores DOM er loadet. 
     */
    @Test
    public void test2() throws Exception {
        System.out.println("Test 2. Verify the filter functionality");
        WebElement element = driver.findElement(By.id("filter"));
        element.sendKeys("2002");
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        Assert.assertThat(rows.size(), is(2));
    }

    @Test
    public void test3() throws Exception {
        System.out.println("Test 3. Verify clear filter results in"
                + "having the original 5 rows");
        WebElement element = driver.findElement(By.id("filter"));
        //Burde bruge element.clear(), men det virker ikke for some reason.
        element.sendKeys(Keys.BACK_SPACE);
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        Assert.assertThat(rows.size(), is(5));
    }

    @Test
    public void test4() throws Exception {
        System.out.println("Test 4. Verify sort by year");
        WebElement e = driver.findElement(By.tagName("thead"));
        WebElement year = e.findElements(By.tagName("th")).get(1).findElement(By.id("h_year"));
        year.click();
        WebElement tbody = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        WebElement id1 = rows.get(0).findElement(By.tagName("td"));
        WebElement id2 = rows.get(4).findElement(By.tagName("td"));

        Assert.assertThat(id1.getText(), is("938"));
        Assert.assertThat(id2.getText(), is("940"));
    }

    @Test
    public void test5() throws Exception {
        System.out.println("Test 5. Verify edit function");
        //Find and click the edit link
        WebElement tbody = driver.findElement(By.tagName("tbody"));
        WebElement edit = tbody.findElements(By.tagName("td")).get(7).findElement(By.linkText("Edit"));
        edit.click();

        //Find and change description
        WebElement form = driver.findElement(By.className("form-horizontal"));
        WebElement fDesc = form.findElement(By.id("description"));
        WebElement save = form.findElement(By.id("save"));
        fDesc.clear();
        fDesc.sendKeys("Cool car");
        save.click();

        //Verify changes
        WebElement tr = driver.findElements(By.tagName("tr")).get(2);
        String tDesc = tr.findElements(By.tagName("td")).get(5).getText();

        Assert.assertThat(tDesc, is("Cool car"));
    }

    @Test
    public void test6() throws Exception {
        System.out.println("Test 6. Verify create new with error");
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        WebElement form = driver.findElement(By.className("form-horizontal"));
        WebElement save = form.findElement(By.id("save"));
        WebElement error = driver.findElement(By.id("submiterr"));
        save.click();
        Assert.assertThat(error.getText(), is("All fields are required"));
    }

    @Test
    public void test7() throws Exception {
        System.out.println("Test 7. Verify create new car");
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows;
        WebElement form = driver.findElement(By.className("form-horizontal"));
        WebElement save = form.findElement(By.id("save"));
        WebElement newCar = form.findElement(By.id("new"));
        WebElement year = form.findElement(By.id("year"));
        WebElement reg = form.findElement(By.id("registered"));
        WebElement make = form.findElement(By.id("make"));
        WebElement model = form.findElement(By.id("model"));
        WebElement desc = form.findElement(By.id("description"));
        WebElement price = form.findElement(By.id("price"));
        newCar.click();
        year.sendKeys("2008");
        reg.sendKeys("2002-5-5");
        make.sendKeys("Kia");
        model.sendKeys("Rio");
        desc.sendKeys("As new");
        price.sendKeys("31000");
        save.click();
        rows = e.findElements(By.tagName("tr"));
        Assert.assertThat(rows.size(), is(6));
    }
}
