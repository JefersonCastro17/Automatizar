package com.Mercapleno.questions;

import com.Mercapleno.userinterfaces.CatalogoPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ValidarDetalleProducto {
    public static Question<String> precioDelProducto(String productoNombre) {
        return actor -> Text.of(CatalogoPage.PRECIO_PRODUCTO.of(productoNombre)).answeredBy(actor).trim();
    }
}
