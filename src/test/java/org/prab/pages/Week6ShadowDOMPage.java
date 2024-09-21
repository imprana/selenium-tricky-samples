package org.prab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Week6ShadowDOMPage {
    WebDriver driver;

    private final SearchContext shadowRoot;
    private final By guidIp = By.id("editField");
    private final By generateGUIDBtn = By.id("buttonGenerate");
    private final By copyGUIDBtn = By.id("buttonCopy");

    public Week6ShadowDOMPage(WebDriver driver) {
        this.driver = driver;
        shadowRoot = driver.findElement(By.tagName("guid-generator")).getShadowRoot();
    }

    public void generateGUID() {
        WebElement element = shadowRoot.findElement(generateGUIDBtn);
        element.click();
    }

    public Object copyGUID() throws IOException, UnsupportedFlavorException {
        WebElement element = shadowRoot.findElement(copyGUIDBtn);
        element.click();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Object data = clipboard.getData(DataFlavor.stringFlavor);
        System.out.println(data);
        return data;
    }

    public String getGUIDFromInput() {
        WebElement element = shadowRoot.findElement(guidIp);
        return element.getAttribute("value").trim();
    }
}
