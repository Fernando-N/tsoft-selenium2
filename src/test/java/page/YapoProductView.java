package page;

import driver.WebDriverManager;
import enums.Region;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YapoProductView extends PageBase {

    @FindBy(id = "da_subject")
    private WebElement titulo;

    @FindBy(id = "da_price")
    private WebElement precioTop;

    @FindBy(xpath = "//div[@class = 'box info']//div[@class = 'price price-final']//strong")
    private WebElement precioBottom;

    @FindBy(xpath = "//div[@class = 'box info']//table//tbody//tr[2]//td")
    private WebElement tipo;

    @FindBy(xpath = "//div[@class = 'box info']//table//tbody//tr[3]//td")
    private WebElement codigo;

    @FindBy(xpath = "//div[@class = 'description']//p")
    private WebElement descripcion;

    @FindBy(xpath = "//seller-info")
    private WebElement infoVendedor;

    public YapoProductView(WebDriverManager webDriverManager) {
        super(webDriverManager);
        PageFactory.initElements(webDriverManager.getWebDriver(), this);
    }

    public String obtenerTitulo() {
        return getText(titulo);
    }

    public String obtenerPrecioTop() {
        return getText(precioTop);
    }

    public String obtenerPrecioBottom() {
        return getText(precioBottom);
    }

    public String obtenerTipo() {
        return getText(tipo);
    }

    public String obtenerCodigo() {
        return getText(codigo);
    }

    public String obtenerDescripcion() {
        return getText(descripcion);
    }

    public String obtenerNombreVendedor() {
        return infoVendedor.getAttribute("username");
    }

    public String obtenerRegionYComunaVendedor() {
        return infoVendedor.getAttribute("region");
    }

    public String obtenerSeniorityVendedor() {
        return infoVendedor.getAttribute("seniority");
    }

    public String obtenerTituloPagina() {
        return getWebDriver().getTitle();
    }

}
