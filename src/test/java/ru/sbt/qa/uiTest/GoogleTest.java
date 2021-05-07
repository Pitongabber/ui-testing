package ru.sbt.qa.uiTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class GoogleTest {
    private WebDriver driver;

    @BeforeAll
    static void checkBrowserShim() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
    }

    @Epic("Отображение главного меню")
    @Feature("Тест без использования PageObject")
    @Story("Как тестировщик, я должен знать что можно писать тесты как мудак")
    @Issue("LOX-2")
    @ParameterizedTest(name = "''{0}''")
    @ValueSource(strings = {"Сбербанк", "ВТБ", "Альфа Банк", "Уралсиб"})
    @Description("Поиск разных банков")
    void simpleTest(String bank) {
        WebElement inputElement = driver.findElement(By.name("query"));
        inputElement.sendKeys(bank);
        inputElement.submit();
        System.out.println("Not page object test");
        Allure.addAttachment("Скриншот", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Allure.addAttachment("Тайтл страницы", "text/plain", driver.getTitle(), ".txt");
    }

    @Epic("Отображение главного меню")
    @Feature("Тест без использования PageObject")
    @Story("Как тестировщик, я должен знать что есть нестабильные тесты")
    @Issue("LOX-1")
    @Owner("Евгений Питонов")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Test
    @Description("Нестабильный тест")
    @Disabled
    void simpleFlakyTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomNum == 0) {
            WebElement inputElement = driver.findElement(By.name("q"));
            inputElement.sendKeys("Сбербанк");
            inputElement.submit();
            System.out.println("Not page object test");
            Allure.addAttachment("Скриншот", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            Allure.addAttachment("Тайтл страницы", "text/plain", driver.getTitle(), ".txt");
        } else {
            throw new IllegalArgumentException();
        }

    }

    void demoAttachmentWithAnnotatedMethod() {
        attachScreenshot();
        attachTitle();
    }

    @Attachment
    private byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Тайтл страницы", type = "text/plain", fileExtension = ".txt")
    private byte[] attachTitle() {
        return driver.getTitle().getBytes(StandardCharsets.UTF_8);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
