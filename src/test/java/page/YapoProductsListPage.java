package page;

import driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class YapoProductsListPage extends PageBase {

    @FindBy(xpath = "//input[@id = 'searchtext']")
    private WebElement inputTextoBusqueda;

    @FindBy(xpath = "//div[@id = 'multicom-head']")
    private WebElement selectComuna;

    @FindBy(xpath = "//div[@id = 'multicom-widget']//label")
    private List<WebElement> selectCommunes;

    @FindBy(xpath = "//button[@id = 'searchbutton']")
    private WebElement btnBuscar;

    @FindBy(xpath = "//div[contains(@id, 'div_contenedor_global_ad_itt')]")
    private WebElement divPopup;

    @FindBy(xpath = "//div[contains(text(), '[X Cerrar]')]")
    private WebElement btnCerrarPopup;

    @FindBy(xpath = "//table[@class = 'listing_thumbs']")
    private WebElement productsList;

    public YapoProductsListPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
        PageFactory.initElements(webDriverManager.getWebDriver(), this);
    }

    public boolean ingresarTextoABuscar(String searchText) {
        return sendKeys(inputTextoBusqueda, searchText);
    }

    public boolean seleccionarComuna(String... communes) {
        click(selectComuna);
        List<WebElement> communesWE = selectCommunes.stream()
                .filter(c -> Arrays.stream(communes).anyMatch(cl -> cl.equalsIgnoreCase(c.getText().trim())))
                .collect(Collectors.toList());

        List<Boolean> results = communesWE.stream().map(this::click).collect(Collectors.toList());

        return !results.contains(Boolean.FALSE);
    }

    public boolean cerrarPopup() {
        return waitForElementVisible(divPopup, 5) && click(btnCerrarPopup);
    }

    public boolean clickBtnBuscar() {
        return click(btnBuscar) && implicitWait(5);
    }

    public boolean openProductWithTitle(String productTitle) {
        for (WebElement product: productsList.findElements(By.tagName("tr"))) {
            String titulo = getText(product);
            if (titulo != null && titulo.contains(productTitle)) {
                getActions().moveToElement(product).click().perform();
                implicitWait(5);
                return true;
            }
        }

        return false;
    }

    public int cantidadAvisosEncontrados() {
        return productsList.findElements(By.tagName("tr")).size();
    }

    public String obtenerTituloPagina() {
        return getWebDriver().getTitle();
    }

}
