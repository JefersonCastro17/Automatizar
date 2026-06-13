package com.Mercapleno.questions;

import com.Mercapleno.userinterfaces.CatalogoPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ValidarLoginExitoso {
    public static Question<String> valor() {
        return actor -> Text.of(CatalogoPage.TITULO_PRODUCTOS).answeredBy(actor);
    }
}
