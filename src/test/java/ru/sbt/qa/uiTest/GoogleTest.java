package ru.sbt.qa.uiTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {
    private WebDriver driver;

    @BeforeAll
    public static void checkBrowserShim() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
    }

    @Test
    public void simpleTest() {
        WebElement inputElement = driver.findElement(By.name("q"));
        inputElement.sendKeys("Сбербанк");
        inputElement.submit();
        System.out.println("Not page object test");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
