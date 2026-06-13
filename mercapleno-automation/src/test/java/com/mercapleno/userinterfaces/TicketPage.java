package com.Mercapleno.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class TicketPage {
    public static final Target TITULO_TICKET = Target.the("titulo de confirmacion del ticket")
            .located(By.className("ticket-header"));

    public static final Target MENSAJE_AGRADECIMIENTO = Target.the("mensaje de agradecimiento de la compra")
            .located(By.className("agradecimiento"));
}
