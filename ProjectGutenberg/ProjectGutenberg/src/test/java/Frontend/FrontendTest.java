//package Frontend;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.hamcrest.CoreMatchers.is;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class FrontendTest {
//
//    static WebDriver driver;
//    static String url = "http://localhost:7777/";
//    Thread t;
//
//    @BeforeClass
//    public static void setup() {
//        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.get(url);
//    }
//
//    @AfterClass
//    public static void tearDown() {
//        driver.quit();
//    }
//
//    @Test
//    public void test1() {
//        System.out.println("Test 1. Verify that the DOM has loaded. "
//                + "We do this by checking that all three input fields are present");
//        (new WebDriverWait(driver, 4)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
//            WebElement e = d.findElement(By.className("searchForm"));
//            ArrayList<WebElement> form = new ArrayList();
//            WebElement i1 = d.findElement(By.name("authorInput"));
//            WebElement i2 = d.findElement(By.name("titleInput"));
//            WebElement i3 = d.findElement(By.name("cityInput"));
//            form.add(i1);
//            form.add(i2);
//            form.add(i3);
//            Assert.assertThat(form.size(), is(3));
//            return true;
//        });
//    }
//
//    @Test
//    public void test2() {
//        System.out.println("Test 2. User story 1, AC3."
//                + "Verify that the getBookAuthorByCity works as excepected. ");
//        WebElement table = driver.findElement(By.tagName("tbody"));
//        List<WebElement> rows = table.findElements(By.tagName("tr"));
//        Assert.assertThat(rows.size(), is(0));
//        WebElement input = driver.findElement(By.id("cityInput"));
//        input.sendKeys("London");
//        input.sendKeys(Keys.TAB);
//        try {
//            Thread.sleep(1000);
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        table = driver.findElement(By.tagName("tbody"));
//        rows = table.findElements(By.tagName("tr"));
//        Assert.assertThat(rows.size(), is(2));
//    }
//
//    @Test
//    public void test3() {
//        System.out.println("Test 3. User story 1, AC1");
//        WebElement input = driver.findElement(By.id("cityInput"));
//        input.clear();
//        WebElement table = driver.findElement(By.className("tbody"));
//        List<WebElement> rows = table.findElements(By.tagName("tr"));
//        Assert.assertThat(rows.size(), is(0));
//        input.sendKeys("Karlslunde");
//        input.sendKeys(Keys.TAB);
//        try {
//            Thread.sleep(1000);
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        WebElement err = driver.findElement(By.id("errorText"));
//        table = driver.findElement(By.className("tbody"));
//        rows = table.findElements(By.tagName("tr"));
//
//        Assert.assertThat(rows.size(), is(0));
//        Assert.assertThat(err.getText(), is("Could not find any results"));
//    }
////    @Test
////    public void test4() {
////        System.out.println("Test 2. User story 1, AC2.");
////        WebElement table = driver.findElement(By.tagName("tbody"));
////        List<WebElement> rows = table.findElements(By.tagName("tr"));
////        Assert.assertThat(rows.size(), is(0));
////        WebElement input = driver.findElement(By.id("cityInput"));
////        input.sendKeys("@ustralia");
////        input.sendKeys(Keys.TAB);
////        table = driver.findElement(By.tagName("tbody"));
////        rows = table.findElements(By.tagName("tr"));
////        Assert.assertThat(rows.size(), is(0));
////    }
//}
