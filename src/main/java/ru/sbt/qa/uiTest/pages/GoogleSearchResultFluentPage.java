package ru.sbt.qa.uiTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.sbt.qa.uiTest.elements.SearchResultBox;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearchResultFluentPage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"rso\"]/div[1]/div")
    List<SearchResultBox> searchResultBoxList;

    public GoogleSearchResultFluentPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(
                new HtmlElementLocatorFactory(driver)), this);
        this.driver = driver;
    }

    public GoogleSearchResultFluentPage checkFirstSearchResult(String expectedLabel, String expectedUrl) {
        SearchResultBox firstSearchResultBox = searchResultBoxList.get(0);
        String actualLabel = firstSearchResultBox.getLabel();
        String actualUrl = firstSearchResultBox.getUrl();
        assertAll("Ожидаемый результат поиска не соответствует действительному",
                () -> assertEquals(expectedLabel, actualLabel),
                () -> assertEquals(expectedUrl, actualUrl));
        return this;
    }

    public void printFirstSearchResult() {
        SearchResultBox searchResultBox = searchResultBoxList.get(0);
        System.out.println(searchResultBox.getLabel());
        System.out.println(searchResultBox.getUrl());
    }
}
