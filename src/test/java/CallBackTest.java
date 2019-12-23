import com.sun.org.apache.xpath.internal.operations.Equals;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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

    @Test
    void shouldWork() throws InterruptedException {
        driver.get(" http://localhost:9999");

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("���������");
        elements.get(1).sendKeys("+79127468103");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.", text.trim());
        Thread.sleep(5000);

       /* WebElement form = driver.findElement(By.);
        form.findElement(By.cssSelector("[data-test-id=name]input")).sendKeys("�������");
        form.findElement(By.cssSelector("[data-test-id=phone]input")).sendKeys("+79124768103");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector("[data-test-id=submit]")).click();
        String text=driver.findElement(By.className("order-success")).getText();
        assertEquals("���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.",text.trim());
        Thread.sleep(5000);*/
    }

    @Test
    void shouldNameError() {
        driver.get(" http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("123test");
        elements.get(1).sendKeys("+79127468103");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        assertEquals("��� � ������� �������� �������. ��������� ������ ������� �����, ������� � ������.", text.trim());
    }

    @Test
    void shouldPhoneError() {
        driver.get(" http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("���� ��������");
        elements.get(1).sendKeys("+791274688888");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        assertEquals("������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.", text.trim());
    }
}