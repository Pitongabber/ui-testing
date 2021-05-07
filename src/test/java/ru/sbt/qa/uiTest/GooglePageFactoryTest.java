package ru.sbt.qa.uiTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sbt.qa.uiTest.pages.GoogleMainPage;
import ru.sbt.qa.uiTest.pages.GoogleSearchResultPage;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class GooglePageFactoryTest {
    private WebDriver driver;

    @BeforeAll
    public static void checkBrowserShim() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();

    }

    @Epic("Отображение главного меню")
    @Feature("Тест с использованием PageObject")
    @Story("Как тестировщик, я должен знать что можно писать тесты как человек")
    @TmsLink("TMS-1")
    @Test
    @Description("Данный тест демонстрирует работу PageObject теста")
    public void pageFactoryTest() {
        driver.get("www.sbepbank.ru");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        GoogleMainPage googleMainPage = new GoogleMainPage(driver);
        googleMainPage.search("Сбербанк");
//        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage(driver);
//        googleSearchResultPage.checkFirstSearchResult(
//                "Частным клиентам — СберБанк", "https://www.sberbank.ru/person");
        System.out.println("Not Fluent Test Success");
        demoAttachmentWithAnnotatedMethod();
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
