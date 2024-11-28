import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class AddRemoveElementsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
//        options.addArguments("headless");
//        options.addArguments("incognito");
//        options.addArguments("disable-notification");
        driver= new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }
    @Test
    public void checkAddRemoveElements () {
        // Добавление двух элементов
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
        }
        List<WebElement> buttons2 = driver.findElements(By.className("added-manually"));
        Assert.assertEquals(2, buttons2.size());

        // Удаление одного элемента
        driver.findElement(By.xpath("//*[@id=\"elements\"]/button")).click();
        List<WebElement> button1 = driver.findElements(By.className("added-manually"));
        Assert.assertEquals(1, button1.size());
    }
    @AfterMethod (alwaysRun = true)
    public void tearDown () {
        driver.quit();
    }

}
 