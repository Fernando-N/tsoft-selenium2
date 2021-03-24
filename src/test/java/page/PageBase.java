package page;

import driver.WebDriverManager;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class PageBase {

    private final WebDriver webDriver;
    private final Actions actions;

    public PageBase(WebDriverManager webDriverManager) {
        this.webDriver = webDriverManager.getWebDriver();
        this.actions = new Actions(webDriver);
    }

    protected boolean click(WebElement webElement) {
        try {
            webElement.click();
            return true;
        }catch (ElementNotInteractableException e) {
            System.err.println("Elemento " + webElement.toString() + " no clickeable");
            e.printStackTrace();
            return false;
        }catch (NoSuchElementException e) {
            System.err.println("Elemento " + webElement.toString() + " no encontrado");
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isDisplayed(WebElement webElement) {
        try {
            return Objects.nonNull(webElement) && webElement.isDisplayed();
        }catch (NoSuchElementException e) {
            System.err.println("Elemento " + webElement.toString() + " no encontrado");
            e.printStackTrace();
            return false;
        }
    }

    protected boolean sendKeys(WebElement webElement, CharSequence... charSequence) {
        try {
            webElement.sendKeys(charSequence);
            return true;
        }catch (Exception e) {
            System.err.println("Error al enviar texto a " + webElement.toString());
            e.printStackTrace();
            return false;
        }
    }

    protected boolean waitForElementVisible(WebElement webElement, int seconds) {
        try {
            new WebDriverWait(webDriver, seconds)
                    .until(ExpectedConditions.visibilityOf(webElement));
            return true;
        }catch (TimeoutException e) {
            System.err.println("No se encontro elemento " + webElement.toString() + " tras esperar " + seconds + " segundos");
            e.printStackTrace();
            return false;
        }
    }

    protected boolean implicitWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected String getText(WebElement target) {
        try {
            return target.getText().trim().replace("\n", "");
        }catch (NoSuchElementException e) {
            return null;
        }
    }

    protected Actions getActions() {
        return this.actions;
    }

    protected WebDriver getWebDriver() {
        return this.webDriver;
    }


}
