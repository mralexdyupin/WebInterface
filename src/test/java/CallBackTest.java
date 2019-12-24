import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallBackTest {
    private WebDriver driver;

    @BeforeAll
    static void SetUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\tmp\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    /*Рабочие тесты позитивный сценарий, добавить @DisplayName*/

    @ParameterizedTest
    @CsvFileSource(resources = "/NamePositiveData.csv", numLinesToSkip = 1)
    void TestWebFormV2(String name) {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] .input__control")).sendKeys(name);
        driver.findElement(By.cssSelector("[data-test-id=phone] .input__control")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String result = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", result.trim());
//        Thread.sleep(5000);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NameNegativeData.csv", numLinesToSkip = 1)
    void TestWebFormV3(String name) {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] .input__control")).sendKeys(name);
        driver.findElement(By.cssSelector("[data-test-id=phone] .input__control")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String result = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", result.trim());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PhoneNegativeData.csv", numLinesToSkip = 1)
    void TestWebFormPhone(String phone) {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] .input__control")).sendKeys("Тест тест");
        driver.findElement(By.cssSelector("[data-test-id=phone] .input__control")).sendKeys(phone);
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector(".button")).click();
        String result = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", result.trim());
    }

    @Test
    void TestWebFormV4() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] .input__control")).sendKeys("Тест Тест");
        driver.findElement(By.cssSelector("[data-test-id=phone] .input__control")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector(".button")).click();
        String result = driver.findElement(By.cssSelector("[data-test-id=agreement]")).getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", result);
//        Thread.sleep(5000);
    }
    /*Тесты на валидацию, т.е. с пустыми полями*/
}