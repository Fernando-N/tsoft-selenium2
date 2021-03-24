package page;

import driver.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YapoAccountCreatedPage extends PageBase {

    @FindBy(xpath = "//div[@class = 'create-account-main']//h2[@class = 'title-account title-account-success']")
    private WebElement lblFelicidades;

    @FindBy(xpath = "//div[@class = 'create-account-main']//div[@class = 'tooltip-success blox-blue']//p")
    private WebElement lblEstasAUnPaso;

    public YapoAccountCreatedPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
        PageFactory.initElements(webDriverManager.getWebDriver(), this);
    }

    public String obtenerTextoFelicitaciones() {
        return getText(lblFelicidades);
    }

    public String obtenerTextoEstasAUnPaso() {
        return getText(lblEstasAUnPaso);
    }

}
