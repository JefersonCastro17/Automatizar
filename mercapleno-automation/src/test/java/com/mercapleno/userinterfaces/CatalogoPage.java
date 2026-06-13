package com.Mercapleno.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CatalogoPage {
    public static final Target TITULO_PRODUCTOS = Target.the("titulo de la pagina de productos")
            .located(By.xpath("//div[@class='catalog-title']/h1"));

    public static final Target BOTON_AGREGAR_AL_CARRITO = Target.the("boton agregar al carrito del producto {0}")
            .locatedBy("//div[@class='producto' and @data-name='{0}']//button[@class='botoncito_producto']");

    public static final Target BOTON_CARRITO_HEADER = Target.the("boton de ir al carrito en el header")
            .located(By.xpath("//header//button[contains(text(), 'Carrito')]"));

    public static final Target BOTON_CERRAR_SESION = Target.the("boton de cerrar sesion")
            .located(By.xpath("//button[contains(text(), 'Cerrar Sesión')]"));

    public static final Target INPUT_BUSQUEDA = Target.the("campo de busqueda de productos")
            .located(By.id("nombre"));

    public static final Target NOMBRE_PRODUCTO = Target.the("nombre del producto {0}")
            .locatedBy("//div[@class='producto' and @data-name='{0}']//p[@class='nombre']");

    public static final Target PRECIO_PRODUCTO = Target.the("precio del producto {0}")
            .locatedBy("//div[@class='producto' and @data-name='{0}']//p[@class='precio']");
}
