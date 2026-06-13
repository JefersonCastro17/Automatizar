package com.Mercapleno.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CarritoPage {
    public static final Target SELECT_METODO_PAGO = Target.the("selector de metodo de pago")
            .located(By.className("payment-select"));

    public static final Target BOTON_PAGAR = Target.the("boton de pagar y finalizar compra")
            .located(By.className("pay-btn"));

    public static final Target BOTON_ELIMINAR_PRODUCTO = Target.the("boton de eliminar producto {0} del carrito")
            .locatedBy("//div[contains(@class, 'producto-carrito-item') and .//p[contains(@class, 'producto-carrito-nombre') and contains(text(), '{0}')]]//button[contains(@class, 'btn-eliminar')]");

    public static final Target MENSAJE_CARRITO_VACIO = Target.the("mensaje de carrito vacio")
            .located(By.className("mensaje-vacio"));

    public static final Target VALOR_TOTAL_COMPRA = Target.the("valor total de la compra en el resumen")
            .located(By.xpath("//div[contains(@class, 'total')]/span[2]"));

    public static final Target TEXTO_ERROR_CHECKOUT = Target.the("texto de error de checkout")
            .located(By.className("checkout-error"));
}
