package com.Mercapleno.tasks;

import com.Mercapleno.userinterfaces.CatalogoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AgregarProducto implements Task {
    private final String productoNombre;

    public AgregarProducto(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CatalogoPage.BOTON_AGREGAR_AL_CARRITO.of(productoNombre)),
                Click.on(CatalogoPage.BOTON_CARRITO_HEADER)
        );
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static AgregarProducto alCarrito(String productoNombre) {
        return instrumented(AgregarProducto.class, productoNombre);
    }
}
