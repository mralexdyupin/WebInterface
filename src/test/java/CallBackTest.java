import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallBackTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/NamePositiveData.csv", numLinesToSkip = 1)
    void TestWebFormNamePositive(String name) {
        open("localhost:9999");
        $(By.cssSelector("[data-test-id=name] .input__control")).setValue(name);
        $(By.cssSelector("[data-test-id=phone] .input__control")).setValue("+79999999999");
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(By.cssSelector("[data-test-id=order-success]")).shouldHave(Condition.exactText
                ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NameNegativeData.csv", numLinesToSkip = 1)
    void TestWebNameNegative(String name, String result) {
        open("localhost:9999");
        $(By.cssSelector("[data-test-id=name] .input__control")).setValue(name);
        $(By.cssSelector("[data-test-id=phone] .input__control")).setValue("+79999999999");
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(By.cssSelector("[data-test-id = name] .input__sub")).shouldHave(Condition.exactText(result));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PhoneNegativeData.csv", numLinesToSkip = 1)
    void TestWebFormPhoneNegative(String phone, String result) {
        open("localhost:9999");
        $(By.cssSelector("[data-test-id=name] .input__control")).setValue("Иван Ивано");
        $(By.cssSelector("[data-test-id=phone] .input__control")).setValue(phone);
        $(By.cssSelector("[data-test-id=agreement]")).click();
        $(By.cssSelector(".button")).click();
        $(By.cssSelector("[data-test-id = phone] .input__sub")).shouldHave(Condition.exactText(result));
    }

    @Test
    void TestWebFormAgreementNegative() {
        open("localhost:9999");
        $(By.cssSelector("[data-test-id=name] .input__control")).setValue("Иван Иванов");
        $(By.cssSelector("[data-test-id=phone] .input__control")).setValue("+79998887766");
        $(By.cssSelector(".button")).click();
        $(By.cssSelector("[data-test-id=agreement]")).should(Condition.cssValue("color",
                "rgba(255, 92, 92, 1)"));
    }
}