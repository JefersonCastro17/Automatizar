package com.Mercapleno.tasks;

import com.Mercapleno.userinterfaces.CarritoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RealizarPago implements Task {
    private final String metodoPago;

    public RealizarPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String metodoVisible = metodoPago;
        if (metodoPago.equalsIgnoreCase("Tarjeta")) {
            metodoVisible = "Tarjeta de Credito";
        } else if (metodoPago.equalsIgnoreCase("Yape")) {
            metodoVisible = "Nequi";
        } else if (metodoPago.equalsIgnoreCase("Plin")) {
            metodoVisible = "Daviplata";
        }
        
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(metodoVisible).from(CarritoPage.SELECT_METODO_PAGO),
                Click.on(CarritoPage.BOTON_PAGAR)
        );
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static RealizarPago conMetodo(String metodoPago) {
        return instrumented(RealizarPago.class, metodoPago);
    }
}
