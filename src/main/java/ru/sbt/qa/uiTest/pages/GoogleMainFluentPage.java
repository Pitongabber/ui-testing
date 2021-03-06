package ru.sbt.qa.uiTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class GoogleMainFluentPage {
    private final WebDriver driver;

    @FindBy(name = "q")
    private TextInput searchInput;

    public GoogleMainFluentPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(
                new HtmlElementLocatorFactory(driver)), this);
        this.driver = driver;
    }

    public GoogleSearchResultFluentPage search(String searchingText) {
        searchInput.sendKeys(searchingText);
        searchInput.submit();
        return new GoogleSearchResultFluentPage(driver);
    }
}
