package ru.sbt.qa.uiTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sbt.qa.uiTest.pages.GoogleMainPage;
import ru.sbt.qa.uiTest.pages.GoogleSearchResultPage;

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
        driver.get("https://google.com");
    }

    @Test
    public void pageFactoryTest() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        GoogleMainPage googleMainPage = new GoogleMainPage(driver);
        googleMainPage.search("Сбербанк");
//        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage(driver);
//        googleSearchResultPage.checkFirstSearchResult(
//                "Частным клиентам — СберБанк", "https://www.sberbank.ru/person");
        System.out.println("Not Fluent Test Success");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
