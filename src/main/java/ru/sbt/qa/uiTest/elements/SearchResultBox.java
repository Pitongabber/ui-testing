package ru.sbt.qa.uiTest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class SearchResultBox extends TypifiedElement {

    public SearchResultBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getLabel() {
        return getWrappedElement().findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div[2]/div[1]/a/h3")).getText();
    }

    public String getUrl() {
        return getWrappedElement().findElement(By.xpath("//div[2]/div[1]/a/div/cite")).getText();
    }
}
