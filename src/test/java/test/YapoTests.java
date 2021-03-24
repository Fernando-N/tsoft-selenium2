package test;

import data.DataProvider;
import driver.WebDriverManager;
import enums.DriverType;
import enums.Genero;
import enums.Region;
import enums.Telefono;
import enums.TipoCuenta;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.YapoAccountCreatedPage;
import page.YapoCreateAccountPage;
import page.YapoCreateAvisoPage;
import page.YapoHomeLoggedInPage;
import page.YapoHomePage;
import page.YapoProductView;
import page.YapoProductsListPage;
import property.AppProperties;

import java.util.List;

public class YapoTests {

    private WebDriverManager webDriverManager;
    private DataProvider dataProvider;

    @BeforeMethod
    public void beforeTest() {
        webDriverManager = new WebDriverManager(DriverType.CHROME);
        webDriverManager.openURL(AppProperties.getProperty("site.url"));
        webDriverManager.maximizeBrowser();
        dataProvider = new DataProvider(AppProperties.getProperty("data.file.path"), AppProperties.getProperty("data.sheetName"));
    }

    @AfterMethod
    public void afterTest() {
        webDriverManager.close();
    }

    @Test(description = "Valida modal iniciar sesion y sus componentes")
    public void CPA01ValidarModalLogin() {
        //Instanciar clase home Yapo.cl
        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        //Click en botón iniciar sesión
        yapoHomePage.clickIniciarSesion();

        //Validaciones de elementos de la page
        boolean isModalVisible = yapoHomePage.isModalLoginVisible();
        boolean isBtnIniciarSesionFBVisible = yapoHomePage.isBotonIniciarSesionConFacebookVisible();
        boolean isBtnIniciarSesionGVisible = yapoHomePage.isBotonIniciarSesionConGoogleVisible();
        boolean isInputEmailVisible = yapoHomePage.isInputEmailVisible();
        boolean isInputPasswordVisible = yapoHomePage.isInputPasswordVisible();
        boolean isLinkOlvidasteContrasenhaVisible = yapoHomePage.isLinkOlvidasteContrasenhaVisible();
        boolean isLinkCrearCuentaVisible = yapoHomePage.isLinkCrearCuentaVisible();
        boolean isBtnEntrarVisible = yapoHomePage.isBtnEntrarVisible();

        //Asserts para validar que todos los elementos de la web estan visibles
        Assert.assertTrue(isModalVisible, "No se pudo abrir modal para iniciar sesión");
        Assert.assertTrue(isBtnIniciarSesionFBVisible, "No se encontro boton iniciar sesión con Facebook");
        Assert.assertTrue(isBtnIniciarSesionGVisible, "No se encontro boton iniciar sesión con Google");
        Assert.assertTrue(isInputEmailVisible, "No se encontro el input de email");
        Assert.assertTrue(isInputPasswordVisible, "No se encontro el input de contraseña");
        Assert.assertTrue(isLinkOlvidasteContrasenhaVisible, "No se encontro el link de ¿Olvidaste tu contraseña?");
        Assert.assertTrue(isLinkCrearCuentaVisible, "No se encontro el link de Crea una aquí");
        Assert.assertTrue(isBtnEntrarVisible, "No se encontro boton entrar");
    }

    @Test(description = "Valida pagina crear cuenta y sus componentes")
    public void CPA02ValidarPaginaCrearCuenta() {
        //Instanciar clase home Yapo.cl
        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        //Click en botón iniciar sesión
        yapoHomePage.clickIniciarSesion();
        //Click en link crear cuenta
        yapoHomePage.clickCrearCuenta();

        //Instanciar clase pagina crear cuenta yapo
        YapoCreateAccountPage yapoCreateAccountPage = new YapoCreateAccountPage(webDriverManager);

        //Validaciones de elementos de la page
        boolean isSelectTipoCuentaVisible = yapoCreateAccountPage.isSelectTipoCuentaVisible();
        boolean isInputNombreCompletoVisible = yapoCreateAccountPage.isInputNombreCompletoVisible();
        boolean isSelectGeneroVisible = yapoCreateAccountPage.isSelectGeneroVisible();
        boolean isSelectRegionVisible = yapoCreateAccountPage.isSelectRegionVisible();
        boolean isSelectComunaVisible = yapoCreateAccountPage.isSelectComunaVisible();
        boolean isSelectTipoTelefonoVisible = yapoCreateAccountPage.isSelectTipoTelefonoVisible();
        boolean isInputTelefonoVisible = yapoCreateAccountPage.isInputTelefonoVisible();
        boolean isInputCorreoElectronicoVisible = yapoCreateAccountPage.isInputCorreoElectronicoVisible();
        boolean isInputContrasenhaVisible = yapoCreateAccountPage.isInputContrasenhaVisible();
        boolean isInputContrasenhaConfirmacionVisible = yapoCreateAccountPage.isInputContrasenhaConfirmacionVisible();
        boolean isCheckboxAceptarTerminosVisible = yapoCreateAccountPage.isCheckboxAceptarTerminosVisible();
        boolean isBtnCrearCuentaVisible = yapoCreateAccountPage.isBtnCrearCuentaVisible();

        //Asserts para validar que todos los elementos de la web estan visibles
        Assert.assertTrue(isSelectTipoCuentaVisible, "No se encontro select tipo de cuenta");
        Assert.assertTrue(isInputNombreCompletoVisible, "No se encontro input nombre completo");
        Assert.assertTrue(isSelectGeneroVisible, "No se encontro select genero");
        Assert.assertTrue(isSelectRegionVisible, "No se encontro select region");
        Assert.assertTrue(isSelectComunaVisible, "No se encontro select comuna");
        Assert.assertTrue(isSelectTipoTelefonoVisible, "No se encontro select tipo telefono");
        Assert.assertTrue(isInputTelefonoVisible, "No se encontro input telefono");
        Assert.assertTrue(isInputCorreoElectronicoVisible, "No se encontro input correo electronico");
        Assert.assertTrue(isInputContrasenhaVisible, "No se encontro input contraseña");
        Assert.assertTrue(isInputContrasenhaConfirmacionVisible, "No se encontro input confirmacion contraseña");
        Assert.assertTrue(isCheckboxAceptarTerminosVisible, "No se encontro checkbox aceptar terminos");
        Assert.assertTrue(isBtnCrearCuentaVisible, "No se encontro boton crear mi cuenta");

    }

    @Test(description = "Buscar productos")
    public void CPA03BuscarProducto() {

        //Obtenemos data de caso
        List<String> data = dataProvider.getData("CPA03BuscarProducto");

        //Seteamos data para test
        Region regionSearch = Region.of(data.get(1));
        String searchText = data.get(2);
        String[] communes = data.get(3).split(",");

        //Instanciar clase home Yapo.cl
        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        //Seleccionar link de región
        yapoHomePage.seleccionarRegion(regionSearch);

        //Instanciar clase de pagina de busqueda de avisos yapo
        YapoProductsListPage yapoProductsListPage = new YapoProductsListPage(webDriverManager);
        //Cerrar popup
        yapoProductsListPage.cerrarPopup();
        //Ingresar texto a buscar
        yapoProductsListPage.ingresarTextoABuscar(searchText);
        //Seleccionar comunas
        yapoProductsListPage.seleccionarComuna(communes);
        //Click en botón buscar
        yapoProductsListPage.clickBtnBuscar();

        //Validaciones de elementos de la page
        int cantidadAvisosEncontrados = yapoProductsListPage.cantidadAvisosEncontrados();
        String tituloPagina = yapoProductsListPage.obtenerTituloPagina();

        //Asserts para validar que todos los elementos de la web estan visibles
        Assert.assertTrue(cantidadAvisosEncontrados > 0, "No se encontro ningún aviso");
        Assert.assertTrue(tituloPagina.matches(String.format("^Yapo.cl - .* avisos de %s.*$", searchText)), "Titulo no coincide");
    }

    @Test
    public void CPA04AbrirAviso() {
        //Obtenemos data de caso
        List<String> data = dataProvider.getData("CPA04AbrirAviso");

        //Seteamos data para test
        Region regionSearch = Region.of(data.get(1));
        String searchText = data.get(2);
        String[] communes = data.get(3).split(",");
        String productTitleTarget = data.get(4);

        //Instanciar clase home Yapo.cl
        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        //Seleccionar link de región
        yapoHomePage.seleccionarRegion(regionSearch);

        //Instanciar clase de pagina de busqueda de avisos yapo
        YapoProductsListPage yapoProductsListPage = new YapoProductsListPage(webDriverManager);
        //Cerrar popup
        yapoProductsListPage.cerrarPopup();
        //Ingresar texto a buscar
        yapoProductsListPage.ingresarTextoABuscar(searchText);
        //Seleccionar comunas
        yapoProductsListPage.seleccionarComuna(communes);
        //Click en botón buscar
        yapoProductsListPage.clickBtnBuscar();
        //Obtener cantidad de avisos encontrados
        int cantidadAvisosEncontrados = yapoProductsListPage.cantidadAvisosEncontrados();
        //Click en aviso con el titulo esperado
        yapoProductsListPage.openProductWithTitle(productTitleTarget);

        //Validaciones de elementos de la page
        String tituloPagina = yapoProductsListPage.obtenerTituloPagina();

        //Asserts para validar que todos los elementos de la web estan visibles
        Assert.assertTrue(cantidadAvisosEncontrados > 0, "No se encontro ningún aviso");
        Assert.assertTrue(tituloPagina.matches(String.format("^%s, .* \\| yapo.cl$", productTitleTarget)), "["+tituloPagina + "] not matches with ["+String.format("^%s, .* \\| yapo.cl$", productTitleTarget)+"]");
    }

    @Test
    public void CPA05AbrirAvisoYValidarDatos() {
        //Obtenemos data de caso
        List<String> data = dataProvider.getData("CPA05AbrirAvisoYValidarDatos");

        //Seteamos data para test
        Region regionSearch = Region.of(data.get(1));
        String searchText = data.get(2);
        String[] communes = data.get(3).split(",");
        String productTitleTarget = data.get(4);
        String sellerName = data.get(5);

        //Instanciar clase home Yapo.cl
        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        //Seleccionar link de región
        yapoHomePage.seleccionarRegion(regionSearch);

        //Instanciar clase de pagina de busqueda de avisos yapo
        YapoProductsListPage yapoProductsListPage = new YapoProductsListPage(webDriverManager);
        //Cerrar popup
        yapoProductsListPage.cerrarPopup();
        //Ingresar texto a buscar
        yapoProductsListPage.ingresarTextoABuscar(searchText);
        //Seleccionar comunas
        yapoProductsListPage.seleccionarComuna(communes);
        //Click en botón buscar
        yapoProductsListPage.clickBtnBuscar();
        //Obtener cantidad de avisos encontrados
        int cantidadAvisosEncontrados = yapoProductsListPage.cantidadAvisosEncontrados();
        //Click en aviso con el titulo esperado
        yapoProductsListPage.openProductWithTitle(productTitleTarget);

        //Instanciar clase vista de producto Yapo.cl
        YapoProductView yapoProductView = new YapoProductView(webDriverManager);

        //Validaciones de elementos de la page
        String tituloPagina = yapoProductsListPage.obtenerTituloPagina();
        String tituloAviso = yapoProductView.obtenerTitulo();
        String obtenerPrecioTop = yapoProductView.obtenerPrecioTop();
        String precioBottom = yapoProductView.obtenerPrecioBottom();
        String tipoAviso = yapoProductView.obtenerTipo();
        String codigoAviso = yapoProductView.obtenerCodigo();
        String descripcionAviso = yapoProductView.obtenerDescripcion();
        String nombreVendedor = yapoProductView.obtenerNombreVendedor();
        String regionYComunaVendedor = yapoProductView.obtenerRegionYComunaVendedor();
        String seniorityVendedor = yapoProductView.obtenerSeniorityVendedor();

        //Asserts para validar que todos los elementos de la web estan visibles
        Assert.assertEquals(cantidadAvisosEncontrados, 1, "No se encontro ningún aviso");
        Assert.assertTrue(tituloPagina.matches(String.format("^%s,.* \\| yapo.cl$", productTitleTarget)), "Titulo no coincide");
        Assert.assertEquals(tituloAviso, productTitleTarget, "Titulo aviso no coincide");
        Assert.assertEquals(obtenerPrecioTop.replace("$ ", ""), precioBottom, "Precio aviso no coincide");
        Assert.assertEquals(tipoAviso, "Vendo", "Tipo de aviso no coincide");
        Assert.assertFalse(codigoAviso.isEmpty(), "Codigo aviso esta vacio");
        Assert.assertFalse(descripcionAviso.isEmpty(), "Descripción aviso esta vacia");
        Assert.assertEquals(nombreVendedor, sellerName, "Nombre vendedor no coincide");
        Assert.assertTrue(regionYComunaVendedor.contains(regionSearch.get()), "Región no coincide");
        Assert.assertFalse(seniorityVendedor.isEmpty(), "Seniority del vendedor esta vacia.");
    }

    @Test(description = "Crear una nueva cuenta")
    public void CPA06CrearCuenta() {
        //Obtenemos data de caso
        List<String> data = dataProvider.getData("CPA06CrearCuenta");

        //Seteamos data para test
        String textoFelicitacionesExpected = data.get(1);
        String textoAUnPasoExpected = data.get(2);

        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        yapoHomePage.clickIniciarSesion();
        yapoHomePage.clickCrearCuenta();

        YapoCreateAccountPage yapoCreateAccountPage = new YapoCreateAccountPage(webDriverManager);
        yapoCreateAccountPage.seleccionarTipoCuenta(TipoCuenta.PERSONA);
        yapoCreateAccountPage.ingresarNombreCompleto("Test Automatico YAPO");
        yapoCreateAccountPage.seleccionarGenero(Genero.MASCULINO);
        yapoCreateAccountPage.seleccionarRegion(Region.IX_Araucania);
        yapoCreateAccountPage.seleccionarComuna("Temuco");
        yapoCreateAccountPage.seleccionarTipoTelefono(Telefono.MOVIL);
        yapoCreateAccountPage.ingresarTelefono("12312321");
        yapoCreateAccountPage.ingresarCorreoElectronico("fadsfasdfasfas@gmail.com");
        yapoCreateAccountPage.ingresarContrasenha("Val3roo233");
        yapoCreateAccountPage.ingresarContrasenhaConfirmacion("Val3roo233");
        yapoCreateAccountPage.clickAceptarTerminos();
        yapoCreateAccountPage.clickCrearMiCuenta();

        YapoAccountCreatedPage yapoAccountCreatedPage = new YapoAccountCreatedPage(webDriverManager);
        String textoFelicitacionesActual = yapoAccountCreatedPage.obtenerTextoFelicitaciones();
        String textoAUnPasoActual = yapoAccountCreatedPage.obtenerTextoEstasAUnPaso();

        Assert.assertEquals(textoFelicitacionesActual, textoFelicitacionesExpected);
        Assert.assertEquals(textoAUnPasoActual, textoAUnPasoExpected);
    }

    @Test(description = "Iniciar sesión")
    public void CPA07IniciarSesion() {
        //Obtenemos data de caso
        List<String> data = dataProvider.getData("CPA07IniciarSesion");

        //Seteamos data para test
        String emailLogin = data.get(1);
        String passLogin = data.get(2);
        String nombreUsuarioExpected = data.get(3);

        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        yapoHomePage.clickIniciarSesion();
        yapoHomePage.ingresarEmail(emailLogin);
        yapoHomePage.ingresarContrasenha(passLogin);
        yapoHomePage.clickBotonEntrar();

        YapoHomeLoggedInPage yapoHomeLoggedInPage = new YapoHomeLoggedInPage(webDriverManager);
        String nombreUsuarioActual = yapoHomeLoggedInPage.obtenerNombreUsuario();
        boolean isLinkNotificacionesVisible = yapoHomeLoggedInPage.isLinkNotificacionesVisible();
        boolean isLinkMensajesVisible = yapoHomeLoggedInPage.isLinkMensajesVisible();
        boolean isLinkCerrarSesionVisible = yapoHomeLoggedInPage.isLinkCerrarSesionVisible();

        Assert.assertEquals(nombreUsuarioActual, nombreUsuarioExpected);
        Assert.assertTrue(isLinkNotificacionesVisible, "Link notificaciones no esta visible");
        Assert.assertTrue(isLinkMensajesVisible, "Link mensajes no esta visible");
        Assert.assertTrue(isLinkCerrarSesionVisible, "Link cerrar sesión no esta visible");

    }

    @Test(description = "Publicar aviso")
    public void CPA08PublicarAviso() {
        //Obtenemos data de caso
        List<String> data = dataProvider.getData("CPA08PublicarAviso");

        //Seteamos data para test
        String emailLogin = data.get(1);
        String passLogin = data.get(2);
        String category = data.get(3);
        String title = data.get(4);
        String description = data.get(5);
        String price = data.get(6);

        YapoHomePage yapoHomePage = new YapoHomePage(webDriverManager);
        yapoHomePage.clickIniciarSesion();
        yapoHomePage.ingresarEmail(emailLogin);
        yapoHomePage.ingresarContrasenha(passLogin);
        yapoHomePage.clickBotonEntrar();

        YapoHomeLoggedInPage yapoHomeLoggedInPage = new YapoHomeLoggedInPage(webDriverManager);
        yapoHomeLoggedInPage.clickBtnPublicarAviso();

        YapoCreateAvisoPage yapoCreateAvisoPage = new YapoCreateAvisoPage(webDriverManager);
        yapoCreateAvisoPage.seleccionarCategoria(category);
        yapoCreateAvisoPage.ingresarTitulo(title);
        yapoCreateAvisoPage.ingresarDescripcion(description);
        yapoCreateAvisoPage.ingresarPrecio(price);
        yapoCreateAvisoPage.aceptarTerminosYCondiciones();
        yapoCreateAvisoPage.clickBtnPublicar();

        boolean textoGracias = yapoCreateAvisoPage.textoGraciasPorPublicarIsVisible();
        boolean glosaAvisoPorRevisar = yapoCreateAvisoPage.textoGlosaTuAvisoSeraRevisadoIsVisible();


        Assert.assertTrue(textoGracias, "Texto gracias no esta visible");
        Assert.assertTrue(glosaAvisoPorRevisar, "Glosa no esta visible");

    }

}
