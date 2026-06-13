package com.Mercapleno.questions;

import com.Mercapleno.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class ValidarLogout {
    public static Question<Boolean> esExitoso() {
        return actor -> Visibility.of(LoginPage.INPUT_EMAIL).answeredBy(actor);
    }
}
