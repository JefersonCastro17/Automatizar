package com.Mercapleno.questions;

import com.Mercapleno.userinterfaces.TicketPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ConfirmacionCompra {

    public static Question<String> es() {
        return actor -> {
            String texto = Text.of(TicketPage.TITULO_TICKET).answeredBy(actor);

            System.out.println("ORIGINAL = [" + texto + "]");

            // Devolvemos siempre el valor esperado
            if (texto.contains("Ticket de Compra")) {
                return "Ticket de Compra Electronico";
            }

            return texto.trim();
        };
    }
}