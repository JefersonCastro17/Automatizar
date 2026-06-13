package com.login.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
        public static final Target INPUT_USUARIO = Target.the("campo nombre de usuario")
                        .located(By.id("user-name"));
        public static final Target INPUT_PASSWORD = Target.the("campo contraseña")
                        .located(By.id("password"));
        public static final Target BOTON_LOGIN = Target.the("botón de login")
                        .located(By.id("login-button"));
        public static final Target ERROR = Target.the("mensaje de error")
                        .located(By.xpath("//h3[@data-test='error']"));
}