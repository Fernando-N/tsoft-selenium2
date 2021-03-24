package page;

import driver.WebDriverManager;
import enums.Region;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YapoHomePage extends PageBase {

    @FindBy(xpath = "//span[text() = 'Iniciar sesi\u00F3n']//parent::a")
    private WebElement btnIniciarSesion;

    @FindBy(xpath = "//div[@id = 'pgwModal']")
    private WebElement modalIniciarSesion;

    @FindBy(xpath = "//div[@id = 'pgwModal']//span[contains(text(), 'Ingresa con Facebook')]//parent::button")
    private WebElement btnIniciarSesionConFacebook;

    @FindBy(xpath = "//div[@id = 'pgwModal']//span[contains(text(), 'Ingresa con Google')]//parent::*//parent::*[@id = 'customGoogleBtn']")
    private WebElement btnIniciarSesionConGoogle;

    @FindBy(xpath = "//div[@id = 'pgwModal']//form//input[@type = 'email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//div[@id = 'pgwModal']//form//input[@type = 'password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[@id = 'pgwModal']//form//a[contains(text(), '\u00BFOlvidaste tu contrase\u00F1a?')]")
    private WebElement linkOlvidasteContrasenha;

    @FindBy(xpath = "//div[@id = 'pgwModal']//form//a[contains(text(), 'Crea una aqu')]")
    private WebElement linkCrearCuenta;

    @FindBy(xpath = "//div[@id = 'pgwModal']//form//button[text() = 'Entrar']")
    private WebElement btnEntrar;

    @FindBy(xpath = "//ul[@class = 'region-links']")
    private List<WebElement> regionLinks;

    @FindBy(xpath = "//span[@class = 'header-userName']")
    private WebElement lblUsername;

    public YapoHomePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
        PageFactory.initElements(webDriverManager.getWebDriver(), this);
    }

    public boolean clickIniciarSesion() {
        click(btnIniciarSesion);
        return waitForElementVisible(modalIniciarSesion, 5);
    }

    public boolean clickCrearCuenta() {
        boolean result = click(linkCrearCuenta);

        if (result) {
            implicitWait(5);
        }

        return result;
    }

    public boolean isModalLoginVisible() {
        return isDisplayed(modalIniciarSesion);
    }

    public boolean isBotonIniciarSesionConFacebookVisible() {
        return isDisplayed(btnIniciarSesionConFacebook);
    }

    public boolean isBotonIniciarSesionConGoogleVisible() {
        return isDisplayed(btnIniciarSesionConGoogle);
    }

    public boolean isInputEmailVisible() {
        return isDisplayed(inputEmail);
    }

    public boolean ingresarEmail(String email) {
        return sendKeys(inputEmail, email);
    }

    public boolean ingresarContrasenha(String contrasenha) {
        return sendKeys(inputPassword, contrasenha);
    }

    public boolean isInputPasswordVisible() {
        return isDisplayed(inputPassword);
    }

    public boolean isLinkOlvidasteContrasenhaVisible() {
        return isDisplayed(linkOlvidasteContrasenha);
    }

    public boolean isLinkCrearCuentaVisible() {
        return isDisplayed(linkCrearCuenta);
    }

    public boolean isBtnEntrarVisible() {
        return isDisplayed(btnEntrar);
    }

    public boolean clickBotonEntrar() {
        return click(btnEntrar) && waitForElementVisible(lblUsername, 5);
    }

    public boolean seleccionarRegion(Region region) {
        WebElement regionLink = regionLinks.stream()
                .map(r -> r.findElement(By.xpath(String.format("//a[contains(text(), '%s')]", region.get()))))
                .filter(this::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Error, región %s", region.get())));

        return click(regionLink);
    }

}
