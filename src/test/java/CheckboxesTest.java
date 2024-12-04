import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void checkCheckboxes() {
        SoftAssert softAssert = new SoftAssert();
        // Чек-бокс первый
        WebElement checkBoxElement1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        boolean isSelectedBeforeClick1 = checkBoxElement1.isSelected();
        softAssert.assertFalse(isSelectedBeforeClick1);
        checkBoxElement1.click();
        boolean isSelectedAfterClick1 = checkBoxElement1.isSelected();
        softAssert.assertTrue(isSelectedAfterClick1);

        // Чек-бокс второй
        WebElement checkBoxElement2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
        boolean isSelectedBeforeClick2 = checkBoxElement2.isSelected();
        softAssert.assertTrue(isSelectedBeforeClick2);
        checkBoxElement2.click();
        boolean isSelectedAfterClick2 = checkBoxElement2.isSelected();
        softAssert.assertFalse(isSelectedAfterClick2);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

