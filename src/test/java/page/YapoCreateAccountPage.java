package page;

import driver.WebDriverManager;
import enums.Genero;
import enums.Region;
import enums.Telefono;
import enums.TipoCuenta;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class YapoCreateAccountPage extends PageBase {

    @FindBy(xpath = "//label[contains(text(), 'Tipo de Cuenta')]//following-sibling::div")
    private WebElement divTipoCuenta;

    @FindBy(name = "name")
    private WebElement inputNombreCompleto;

    @FindBy(xpath = "//label[contains(text(), 'G\u00E9nero')]//following-sibling::div")
    private WebElement divGenero;

    @FindBy(name = "region")
    private WebElement selectRegion;

    @FindBy(name = "commune")
    private WebElement selectComuna;

    @FindBy(xpath = "//label[contains(text(), 'Tel\u00E9fono')]//following-sibling::div")
    private WebElement divTelefono;

    @FindBy(name = "phone")
    private WebElement inputTelefono;

    @FindBy(name = "email")
    private WebElement inputCorreoElectronico;

    @FindBy(name = "password")
    private WebElement inputContrasenha;

    @FindBy(name = "password_verify")
    private WebElement inputContrasenhaConfirmation;

    @FindBy(id = "label_accept_conditions")
    private WebElement lblAceptarTerminos;

    @FindBy(id = "edit_profile_btn")
    private WebElement btnCrearMiCuenta;

    public YapoCreateAccountPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
        PageFactory.initElements(webDriverManager.getWebDriver(), this);
    }

    public boolean seleccionarTipoCuenta(TipoCuenta tipoCuenta) {
        By xpath = By.xpath(String.format("//span[text() = '%s']", tipoCuenta.get()));
        WebElement webElement = divTipoCuenta.findElement(xpath);
        return click(webElement);
    }

    public boolean isSelectTipoCuentaVisible() {
        return isDisplayed(divTipoCuenta);
    }

    public boolean ingresarNombreCompleto(String nombreCompleto) {
        return sendKeys(inputNombreCompleto, nombreCompleto);
    }

    public boolean isInputNombreCompletoVisible() {
        return isDisplayed(inputNombreCompleto);
    }

    public boolean seleccionarGenero(Genero genero) {
        By xpath = By.xpath(String.format("//span[text() = '%s']", genero.get()));
        WebElement webElement = divGenero.findElement(xpath);
        return click(webElement);
    }

    public boolean isSelectGeneroVisible() {
        return isDisplayed(divGenero);
    }

    public boolean seleccionarRegion(Region region) {
        new Select(selectRegion).selectByVisibleText(region.get());
        return true;
    }

    public boolean isSelectRegionVisible() {
        return isDisplayed(selectRegion);
    }

    public boolean seleccionarComuna(String comuna) {
        new Select(selectComuna).selectByVisibleText(comuna);
        return true;
    }

    public boolean isSelectComunaVisible() {
        return isDisplayed(selectComuna);
    }

    public boolean seleccionarTipoTelefono(Telefono telefono) {
        By xpath = By.xpath(String.format("//label[contains(text(), '%s')]", telefono.get()));
        WebElement webElement = divTelefono.findElement(xpath);
        return click(webElement);
    }

    public boolean isSelectTipoTelefonoVisible() {
        return isDisplayed(divTelefono);
    }

    public boolean ingresarTelefono(String telefono) {
        return sendKeys(inputTelefono, telefono);
    }

    public boolean isInputTelefonoVisible() {
        return isDisplayed(inputTelefono);
    }

    public boolean ingresarCorreoElectronico(String correoElectronico) {
        return sendKeys(inputCorreoElectronico, correoElectronico);
    }

    public boolean isInputCorreoElectronicoVisible() {
        return isDisplayed(inputCorreoElectronico);
    }

    public boolean ingresarContrasenha(String contrasenha) {
        return sendKeys(inputContrasenha, contrasenha);
    }

    public boolean isInputContrasenhaVisible() {
        return isDisplayed(inputContrasenha);
    }

    public boolean ingresarContrasenhaConfirmacion(String contrasenhaConfirmacion) {
        return sendKeys(inputContrasenhaConfirmation, contrasenhaConfirmacion);
    }

    public boolean isInputContrasenhaConfirmacionVisible() {
        return isDisplayed(inputContrasenhaConfirmation);
    }

    public boolean clickAceptarTerminos() {
        return click(lblAceptarTerminos);
    }

    public boolean isCheckboxAceptarTerminosVisible() {
        return isDisplayed(lblAceptarTerminos);
    }

    public boolean clickCrearMiCuenta() {
        return click(btnCrearMiCuenta);
    }

    public boolean isBtnCrearCuentaVisible() {
        return isDisplayed(btnCrearMiCuenta);
    }

}
