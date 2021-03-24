package driver;

import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import property.AppProperties;

public class WebDriverManager {

    private final WebDriver webDriver;

    public WebDriverManager(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                AppProperties.setChromeProperties();
                this.webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                AppProperties.setFirefoxProperties();
                this.webDriver = new FirefoxDriver();
                break;
            case IE:
                AppProperties.setIEProperties();
                this.webDriver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("Driver no encontrado para " + driverType);
        }
    }

    public void openURL(String url) {
        this.webDriver.get(url);
    }

    public void close() {
        this.webDriver.close();
    }

    public void maximizeBrowser() {
        this.webDriver.manage().window().maximize();
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

}
