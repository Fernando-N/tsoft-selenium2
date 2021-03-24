package page;

import driver.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YapoHomeLoggedInPage extends PageBase {

    @FindBy(xpath = "//span[@class = 'header-userName']")
    private WebElement lblUsername;

    @FindBy(xpath = "//span[@class = 'header-messagesMailText']")
    private WebElement linkNotificaciones;

    @FindBy(xpath = "//span[@class = 'header-messagesMailText']")
    private WebElement linkMensajes;

    @FindBy(xpath = "//span[@class = 'header-userLogoutText']")
    private WebElement linkCerrarSesion;

    @FindBy(xpath = "//span[contains(text(), 'Publicar aviso')]//parent::a")
    private WebElement btnPublicarAviso;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement lblPublicarAviso;

    public YapoHomeLoggedInPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
        PageFactory.initElements(webDriverManager.getWebDriver(), this);
    }

    public String obtenerNombreUsuario() {
        return getText(lblUsername).replace("Hola, ", "");
    }

    public boolean isLinkNotificacionesVisible() {
        return isDisplayed(linkNotificaciones);
    }

    public boolean isLinkMensajesVisible() {
        return isDisplayed(linkMensajes);
    }

    public boolean isLinkCerrarSesionVisible() {
        return isDisplayed(linkCerrarSesion);
    }

    public boolean clickBtnPublicarAviso() {
        return click(btnPublicarAviso) && waitForElementVisible(lblPublicarAviso, 10);
    }

}
