package ru.sbt.qa.uiTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sbt.qa.uiTest.pages.GoogleMainFluentPage;
import ru.sbt.qa.uiTest.pages.GoogleMainPage;
import ru.sbt.qa.uiTest.pages.GoogleSearchResultPage;

import java.util.concurrent.TimeUnit;

public class GooglePageFactoryFluentTest {
    private WebDriver driver;

    @BeforeAll
    public static void checkBrowserShim() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://google.com");
    }

    @Epic("Отображение главного меню")
    @Feature("Тест с использованием Fluent")
    @Story("Как тестировщик, я должен знать что можно писать Fluent тесты")
    @TmsLink("TMS-2")
    @Test
    public void pageFactoryTest() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        new GoogleMainFluentPage(driver)
                .search("Сбербанк");
        System.out.println("Test success");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
