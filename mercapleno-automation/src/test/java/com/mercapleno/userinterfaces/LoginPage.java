package com.Mercapleno.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target INPUT_EMAIL = Target.the("campo de correo electronico")
            .located(By.id("email"));
    public static final Target INPUT_PASSWORD = Target.the("campo de contrasena")
            .located(By.id("password"));
    public static final Target BOTON_INGRESAR = Target.the("boton de ingresar")
            .located(By.cssSelector("button.submit-btn"));
}
