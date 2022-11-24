package com.taf.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@UtilityClass
public class WebActions {

    public void dragDropAction(WebDriver driver, By fromElement, By toElement) {
        Actions builder = new Actions(driver);
        WebElement from = driver.findElement(fromElement);
        WebElement to = driver.findElement(toElement);
        builder.dragAndDrop(from, to).perform();
    }


    public void resize(WebDriver driver, WebElement element, int offsetX, int offsetY)
    {
        int width = element.getSize().getWidth();
        Actions action = new Actions(driver);
        action.moveToElement(element, width, 1);
        action.clickAndHold().moveByOffset(offsetX, offsetY).release();
        action.build().perform();
    }

    public void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickOnElementUsingJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
