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

public class YapoCreateAvisoPage extends PageBase {

    @FindBy(name = "category_group")
    private WebElement selectCategory;

    @FindBy(id = "subject")
    private WebElement txtTitle;

    @FindBy(id = "body")
    private WebElement txtDescription;

    @FindBy(name = "price")
    private WebElement txtPrice;

    @FindBy(xpath = "//ins[@class = 'iCheck-helper']")
    private WebElement lblToS;

    @FindBy(name = "create")
    private WebElement btnPublicarAhora;

    @FindBy(xpath = "//div[@class = 'paymentSuccesful']")
    private WebElement lblGraciasPorPublicar;

    @FindBy(xpath = "//div[@class = 'paymentSuccesful']")
    private WebElement lblAvisoPorRevisar;

    public YapoCreateAvisoPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
        PageFactory.initElements(webDriverManager.getWebDriver(), this);
    }

    public boolean seleccionarCategoria(String categoryName) {
        Select select = new Select(selectCategory);
        select.selectByVisibleText(categoryName);
        return true;
    }

    public boolean ingresarTitulo(String title) {
        return sendKeys(txtTitle, title);
    }

    public boolean ingresarDescripcion(String description) {
        return sendKeys(txtDescription, description);
    }

    public boolean ingresarPrecio(String price) {
        return sendKeys(txtPrice, price);
    }

    public boolean aceptarTerminosYCondiciones() {
        return click(lblToS);
    }

    public boolean clickBtnPublicar() {
        boolean result = click(btnPublicarAhora);

        if (result) {
            implicitWait(5);
        }

        return result;
    }

    public boolean textoGraciasPorPublicarIsVisible() {
        return isDisplayed(lblGraciasPorPublicar);
    }

    public boolean textoGlosaTuAvisoSeraRevisadoIsVisible() {
        return isDisplayed(lblAvisoPorRevisar);
    }
}
