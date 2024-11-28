import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InputsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/inputs");
    }
    @Test
    public void checkInputs() {
        WebElement field = driver.findElement(By.cssSelector("input[type='number']"));

        //Проверка что текст не вводиться в поле
        field.sendKeys("Astaf");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String TextField = field.getText();
        Assert.assertEquals(TextField,"");

        //Проверка что цифры вводиться в поле
        field.sendKeys("123");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String NumberField = field.getAttribute("value");
        Assert.assertEquals(NumberField,"123");
        field.clear();

        //Проверка кнопки ARROW_UP
        field.sendKeys(Keys.ARROW_UP);
        field.sendKeys(Keys.ARROW_UP);
        String ARROW_UP_Field = field.getAttribute("value");
        Assert.assertEquals(ARROW_UP_Field,"2");
        field.clear();

        //Проверка кнопки ARROW_DOWN
        field.sendKeys(Keys.ARROW_DOWN);
        field.sendKeys(Keys.ARROW_DOWN);
        String ARROW_DOWN_Field = field.getAttribute("value");
        Assert.assertEquals(ARROW_DOWN_Field,"-2");
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown () {
       driver.quit();
    }
}
